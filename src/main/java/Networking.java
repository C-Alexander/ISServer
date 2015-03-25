import org.java_websocket.handshake.*;
import org.java_websocket.server.*;
import org.java_websocket.*;
import org.java_websocket.drafts.*;
import com.esotericsoftware.jsonbeans.*;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

/**
 * Created by Alexander on 26/01/2015.
 */
public class Networking extends WebSocketServer {
    Json json = new Json();
    public Networking( int port , Draft d ) throws UnknownHostException {
        super( new InetSocketAddress( port ), Collections.singletonList(d) );
    }

    public Networking( InetSocketAddress address, Draft d ) {
        super( address, Collections.singletonList( d ) );
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        SolarSystemData solTest = new SolarSystemData();
        solTest.name = "Sol";
        PacketData startingPacket = new PacketData();
        startingPacket.solarSystemData = solTest;
        PlanetData earth = new PlanetData();
        earth.name = "Earth";
        earth.texture = "legacy/Body/Earth.gif";
        earth.xPosition = 10;
        earth.yPosition = 10;
        solTest.planets.add(earth);
        conn.send(json.toJson(startingPacket));
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        PlayerManager.getInstance().removePlayer(conn);
    }

    @Override
    public void onError( WebSocket conn, Exception ex ) {
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        System.out.println(json.prettyPrint(message));
        PacketData newPacket = json.fromJson(PacketData.class, message);
        if (newPacket.loginData != null && newPacket.loginData.user_id != -1 && newPacket.loginData.user_hash != null) {
            Player newPlayer = DatabaseManager.getInstance().login(newPacket.loginData.user_id, newPacket.loginData.user_hash);
            if (newPlayer != null) {
                newPlayer.conn = conn;
                PlayerManager.getInstance().addPlayer(newPlayer);
            } else {
                PacketData error = new PacketData();
                error.message = "Player with ID " + newPacket.loginData.user_id + " and hash " + newPacket.loginData.user_hash + " could not be found.";
                conn.send(json.toJson(error));
            }
        }
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer blob ) {
    }


}
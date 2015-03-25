import java.util.ArrayList;
import java.util.Iterator;

import com.esotericsoftware.jsonbeans.*;
import org.java_websocket.*;
/**
 * Created by Alexander on 14/02/2015.
 */
public class PlayerManager {
    private ArrayList<Player> players = new ArrayList<Player>();
    Json json = new Json();

    private static PlayerManager playerManager;

    public static PlayerManager getInstance() {
    if (playerManager != null) {
        return playerManager;
    } else {
        return playerManager = new PlayerManager();
    }
    }


    public void addPlayer(Player player) {
        players.add(player);
        PacketData successData = new PacketData();
        successData.message = "Login successfull!";
        player.conn.send(json.toJson(successData));
        PacketData newPlayer = new PacketData();
        newPlayer.chatData = new ChatData();
        newPlayer.chatData.chatMessage = new ChatMessage();
        newPlayer.chatData.chatMessage.content = player.display_name + " has come online!";
        for (Player p : PlayerManager.getInstance().getPlayers()) {
            p.conn.send(json.toJson(newPlayer));
        }
    }

    public Player getPlayer(WebSocket conn) {
        for (Player player : players) {
            if (player.conn == conn) {
                return player;
            }
        }
            return new Player(-1, "does not exist", -1, -1, -1);
    }

    public void removePlayer(WebSocket conn) {
        Iterator<Player> i = players.iterator();
        while (i.hasNext()) {
            if (i.next().conn == conn) {
                i.remove();
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}

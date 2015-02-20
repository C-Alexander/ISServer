import java.util.ArrayList;
import com.esotericsoftware.jsonbeans.*;
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
    }
}

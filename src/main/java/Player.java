/**
 * Created by Alexander on 14/02/2015.
 */
import org.java_websocket.*;

public class Player {
    int user_id;
    String display_name;
    int user_group;
    int race_id;
    int faction_id;
    WebSocket conn;

    public Player(int user_id, String display_name, int user_group, int race_id, int faction_id) {
        this.user_id = user_id;
        this.display_name = display_name;
        this.user_group = user_group;
        this.race_id = race_id;
        this.faction_id = faction_id;
    }
}

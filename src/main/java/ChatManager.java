import java.util.ArrayList;

/**
 * Created by Alexander on 23/02/2015.
 */
public class ChatManager {
    private static ChatManager chatManager;
    ArrayList<ChatData> chatMessages = new ArrayList<ChatData>();

    public static ChatManager getInstance() {
        if (chatManager == null) {
            return chatManager = new ChatManager();
        } else {
            return chatManager;
        }
    }

}

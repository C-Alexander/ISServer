import com.esotericsoftware.jsonbeans.*;
import org.java_websocket.*;
import org.java_websocket.drafts.Draft_17;

import java.net.UnknownHostException;

/**
 * Created by Alexander on 26/01/2015.
 */
public class ISServer {
    public static void main(String [] args)
    {
        Json json = new Json();
        try {
            Networking networking = new Networking(11984, new Draft_17());
            networking.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        while (true) {
            //System.out.print("Awimoweh ");
        }
    }
}

/**
 * Created by Alexander on 23/02/2015.
 */
public class FactionManager {
    private static FactionManager factionManager;

    public static FactionManager getInstance() {
        if (factionManager == null) {
            return factionManager = new FactionManager();
        } else return factionManager;
    }
}

import java.sql.*;

/**
 * Created by Alexander on 14/02/2015.
 */
public class DatabaseManager {
    final String driver = "com.mysql.jdbc.Driver"; //the mysql driver
    final String location = "jdbc:mysql://localhost/isw_db1";//location of database...

    final String username = "isw_us1";
    final String password = "?Xd_%@wd420C";

    private static DatabaseManager databaseManager = null;

    private Connection connection;

    public DatabaseManager() {
        connect();
    }
    public void connect() { //connect method for the database
        try {
            //Get the jbdc mysql driver
            System.out.println("Finding the database class..");
            Class.forName(driver);
            //connect to the database
            System.out.println("Connecting to the Database..");
            connection = DriverManager.getConnection(location,  username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect() { //disconnect from the database
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
       //     try {
           //     if (statement != null)
          //          statement.close();
            //} catch (SQLException se2) {
            }// nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    public Player login(int user_id, String user_hash) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("select display_name, user_group, race_id, faction_id" +
                    " FROM users " +
                    "WHERE user_id= ? " +
                    "AND user_hash= ? ");
            stmt.setInt(1, user_id);
            stmt.setString(2, user_hash);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Player(user_id, result.getString("display_name"), result.getInt("user_group"), result.getInt("race_id"), result.getInt("faction_id"));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DatabaseManager getInstance() {
        if (databaseManager != null) {
            return databaseManager;
        } else {
            return databaseManager = new DatabaseManager();
        }
    }
    public void checkConnection() {
        try {
            if (!connection.isValid(3)) {
            disconnect();
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

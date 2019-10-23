package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

/**
 * The purpose of DB is to get the Connection to the database It is implemented
 * with Singleton design pattern to make sure there is always only one
 * connection to the database
 * 
 * @author tine
 */
public class DB {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/useradmin";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection singleton; //named to indicate it's design pattern implementation

    public static void setConnection(Connection con) {
        singleton = con;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName(DRIVER);
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }
}

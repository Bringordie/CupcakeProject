package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String dbproperties = "/PATH/TO/DB.PROPERTIESs";

        try (FileInputStream f = new FileInputStream(dbproperties)) {

            // load the properties file
            Properties pros = new Properties();
            pros.load(f);
            Class.forName(DRIVER);
            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            // create a connection to the database
            conn = DriverManager.getConnection(url + "?serverTimezone=UTC", user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}

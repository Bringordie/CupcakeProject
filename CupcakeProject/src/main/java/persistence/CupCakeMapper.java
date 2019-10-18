package persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.SHA256;
import logic.User;
import static persistence.DBConnection.getConnection;


public class CupCakeMapper {
    
    public static void reqisterUser(String username, String password) throws SQLException, ClassNotFoundException {
        
        String sql = "INSERT INTO users(Username, Password, Role)VALUES(?,?,?)";
        
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, false);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    } 
}

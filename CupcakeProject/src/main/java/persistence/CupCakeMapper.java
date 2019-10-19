package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static boolean checkUsername(String username) throws ClassNotFoundException, SQLException {
        int countTotal = 0;
        boolean returnAnswer = true;
        String sql = "select count(username) from users where Username = '" + username + "'";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        try {
            while (result.next()) {
                int getTotal = result.getInt(1);
                countTotal = getTotal;
            }
        } catch (SQLException ex) {
            System.err.println("SQException Error");
        }
        if (countTotal != 0) {
            returnAnswer = true;
        } else {
            returnAnswer = false;
        }
        return returnAnswer;
    }
    
    public static boolean checkPassword(String username, String password) throws ClassNotFoundException, SQLException {
        String hashPassword = SHA256.getHash(password.getBytes());
        Boolean passwordBoolean = false;
        String sql = "select Password from users where Username = '" + username + "'";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        try {
            while (result.next()) {
                String getHash = result.getString(1);
                if (getHash.contains(hashPassword)) {
                    passwordBoolean = true;
                } else {
                    passwordBoolean = false;
                 }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupCakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passwordBoolean;
    }
    
    public static boolean checkRole(String username) throws ClassNotFoundException, SQLException {
        Boolean roleBoolean = false;
        String sql = "select Role from users where Username = '" + username + "'";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        try {
            while (result.next()) {
                int getRole = result.getInt(1);
                 if (getRole != 0) {
                     roleBoolean = true;
                } else {
                     roleBoolean = false;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CupCakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roleBoolean;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(CupCakeMapper.checkRole("Bringordie"));
    }


}

package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Bottom;
import logic.LineItems;
import logic.SHA256;
import logic.Topping;
import static persistence.DBConnection.getConnection;

public class DBFacade implements Facade{

    /**
     * @author Bringordie - Frederik Braagaard
     * @param username - Input from Registration.jsp
     * @param password - Input from Registration.jsp
     */
    public void reqisterUser(String username, String password, String name, String email) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO users(Username, Password, Role, Name, Email)VALUES(?,?,?,?,?)";
        String encrypt = SHA256.getHash(password.getBytes());

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, encrypt);
            statement.setBoolean(3, false);
            statement.setString(4, name);
            statement.setString(5, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     * @param username - Input from Registration.jsp and Login.jsp to see if the
     * username already exists/is taken.
     * @return Method will return either true or false depending on the username
     * already existing in the DB.
     */
    public boolean checkUsername(String username) throws ClassNotFoundException, SQLException {
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

    /**
     * @author Bringordie - Frederik Braagaard
     * @param username - Uses the username for SQL string
     * @param password - Password check for login.jsp
     * @return Method will return either true or false depending on if the
     * encrypted password matches the one in the DB.
     */
    public boolean checkPassword(String username, String password) throws ClassNotFoundException, SQLException {
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
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passwordBoolean;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     * @param username - Role check for access and for navigation after login.
     * @return Method will return either true or false depending on the role
     * used to see if they are a regular user or an admin. And where to navigate
     * them
     */
    public boolean checkRole(String username) throws ClassNotFoundException, SQLException {
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
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roleBoolean;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     * @return returns an arraylist of all the toppings in the DB.
     */
    public ArrayList<Topping> getToppings() throws SQLException, ClassNotFoundException {
        ArrayList<Topping> toppings = new ArrayList();
        String sql = "select * from toppings";
        //ResultSet result = null;
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int idTopping = result.getInt(1);
                String toppingName = result.getString(2);
                double price = result.getDouble(3);
                toppings.add(new Topping(toppingName, price, idTopping));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        //toppings.setToppingsFromDB(toppings);
        //Topping topping = new Topping();
        //toppings.setToppingsFromDB(toppings);
        //Topping topping = new Topping();
        
        
        return toppings;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     * @return returns an arraylist of all the buttoms in the DB.
     */
    public ArrayList<Bottom> getBottoms() throws SQLException, ClassNotFoundException {
        ArrayList<Bottom> bottoms = new ArrayList();
        String sql = "select * from bottoms";
        //ResultSet result = null;
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int idButtom = result.getInt(1);
                String buttomName = result.getString(2);
                double price = result.getDouble(3);
                bottoms.add(new Bottom(buttomName, price, idButtom));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bottoms;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void updateBalance(String username, double Addbalance) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE users set balance = balance +" + Addbalance + " where Username = '" + username + "'";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public double getBalance(String username) throws SQLException, ClassNotFoundException {

        String sql = "select balance from users where Username = '" + username + "'";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        double balancesum = 0;

        try {
            while (result.next()) {
                balancesum = result.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balancesum;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public int getLatestOrderNumber() throws SQLException, ClassNotFoundException {

        String sql = "select count(Ordernumber) from completeorders";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        int latestOrderNumber = 0;
        try {
            while (result.next()) {
                latestOrderNumber = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return latestOrderNumber;
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    // To do: finalize later
    public void createCompletedOrder(int idUser, double totalprice) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO completeorders(idUser, Total_price)VALUES(?,?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, idUser);
            statement.setDouble(2, totalprice);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    // To do logic is missing in the classes to complete this.
    public void createLineItems(ArrayList<LineItems> lineitems) throws SQLException, ClassNotFoundException {

        int createOrderNumber = getLatestOrderNumber() + 1;

        String sql = "INSERT INTO lineitems(idBottom, idTopping, quantity, idUser, Price)VALUES(?,?,?,?,?)";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        int latestOrderNumber = 0;

        for (LineItems lineitem : lineitems) {

        }

    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public String getNameOfUser(String username) throws SQLException, ClassNotFoundException {

        String sql = "select Name from users where Username = '" + username + "'";
        ResultSet result = getConnection().prepareStatement(sql).executeQuery();
        String name = "";
        try {
            while (result.next()) {
                name = result.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

}

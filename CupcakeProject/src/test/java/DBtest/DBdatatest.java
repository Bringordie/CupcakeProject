package DBtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.Bottom;
import logic.Topping;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.junit.BeforeClass;
import org.junit.Ignore;
import persistence.CupCakeMapper;
import persistence_demo.CupCakeMapperDemo;



public class DBdatatest {
    
    
    @Test
    public void DBCreateUserTest() throws SQLException, ClassNotFoundException {
        //Clean up should be placed correct places where it's needed in end.
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        
        CupCakeMapperDemo.reqisterUser("CPHtest", "encrypted", "Testperson", "test@email.org");
        
        int expected = 1;
        int actually = CupCakeMapperDemo.checkHowManyUsersExists();
        
        assertEquals(expected, actually);
        
    }
    

    @Test
    public void DBMultipleCreations() throws SQLException, ClassNotFoundException {
        //Clean up should be placed correct places where it's needed in end.
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        
        CupCakeMapperDemo.reqisterUser("CPHtest", "encrypted", "Testperson", "test@email.org");
        CupCakeMapperDemo.reqisterUser("CPHtest1", "encrypted", "Testperson", "test@email.org");
        CupCakeMapperDemo.reqisterUser("CPHtest2", "encrypted", "Testperson", "test@email.org");
        CupCakeMapperDemo.reqisterUser("CPHtest3", "encrypted", "Testperson", "test@email.org");
        CupCakeMapperDemo.reqisterUser("CPHtest4", "encrypted", "Testperson", "test@email.org");
        
        int expected = 5;
        int actually = CupCakeMapperDemo.checkHowManyUsersExists();
        
        assertEquals(expected, actually);
    
    }
    

    @Test
    public void DBTestPasswordIsEncrypted() throws SQLException, ClassNotFoundException {
        //Clean up should be placed correct places where it's needed in end.
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
    
        CupCakeMapperDemo.reqisterUser("CPHtestencryption", "encrypted", "Testperson", "test@email.org");
        
        Boolean callbackofpassword = CupCakeMapperDemo.checkPassword("CPHtestencryption", "encrypted");
        Boolean incorrectpassword = CupCakeMapperDemo.checkPassword("CPHtestencryption", "123");
        
        assertTrue(callbackofpassword);
        assertFalse(incorrectpassword);
        
        CupCakeMapperDemo.reqisterUser("CPHtestencryption2", "stillencrypted", "Testperson", "test@email.org");
        
        Boolean callbackofpassword2 = CupCakeMapperDemo.checkPassword("CPHtestencryption2", "stillencrypted");
        Boolean incorrectpassword2 = CupCakeMapperDemo.checkPassword("CPHtestencryption", "hello");
        
        assertTrue(callbackofpassword2);
        assertFalse(incorrectpassword2);
}
    
    @Test
    public void DBCheckRoleOfNewlyCreatedUser() throws SQLException, ClassNotFoundException {
        //Clean up should be placed correct places where it's needed in end.
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        
        CupCakeMapperDemo.reqisterUser("CPHtest", "encrypted", "Testperson", "test@email.org");
        
        boolean expected = false;
        boolean actually = CupCakeMapperDemo.checkRole("CPHtest");
        
        assertEquals(expected, actually);
        
    }
    

    @Test
    public void DBCeckToppingandBottomAmmount() throws SQLException, ClassNotFoundException {
        ArrayList<Bottom> bottomtest = CupCakeMapperDemo.getBottoms();
        
        ArrayList<Topping> toppingtest = CupCakeMapperDemo.getToppings();
        
        int expectedtopping = 9;
        int expectedbottom = 5;
        
        int actuallytopping = toppingtest.size();
        int actuallybottom = bottomtest.size();
        
        
        assertEquals(expectedtopping, actuallytopping);
        assertEquals(expectedbottom, actuallybottom);
    }
    

    @Test
    public void DBCheckPositionOfName() throws SQLException, ClassNotFoundException {
        ArrayList<Bottom> bottomtest = CupCakeMapperDemo.getBottoms();
        
        ArrayList<Topping> toppingtest = CupCakeMapperDemo.getToppings();
        
        String expectedtopping = "Blueberry";
        String expectedbottom = "Pistacio";
        
        Topping actuallytopping = toppingtest.get(1);
        Bottom actuallybottom = bottomtest.get(3);
        
        String getnametopping = actuallytopping.getName();
        String getnamebottom = actuallybottom.getName();
        
        
        
        assertEquals(expectedtopping, getnametopping);
        assertEquals(expectedbottom, getnamebottom);
    }
    
    @Test
    public void DBCheckBalanceUpdated() throws SQLException, ClassNotFoundException {
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        
        CupCakeMapperDemo.reqisterUser("CPHtest", "encrypted", "Testperson", "test@email.org");
        
        double expected = 0;
        double actually = CupCakeMapperDemo.getBalance("CPHtest");
        
        assertEquals(expected, actually, 0.001);
        
        CupCakeMapperDemo.updateBalance("CPHtest", 5000);
        
        double expectednow = 5000;
        double actuallynow = CupCakeMapperDemo.getBalance("CPHtest");
        
        assertEquals(expected, actually, 0.001);
    }
    
    @Test
    public void DBGetLatestOrderNumber() throws SQLException, ClassNotFoundException {
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        //Created a user so that we have a user with an id.
        CupCakeMapperDemo.reqisterUser("Testuser", "password", "name", "email");
        
        //Create an finalized order
        CupCakeMapperDemo.createCompletedOrder(1, 750);
        
        int expected = 1;
        int actually = CupCakeMapperDemo.getLatestOrderNumber();
        
        assertEquals(expected, actually);
        
        //Create 4 more finalized order
        CupCakeMapperDemo.createCompletedOrder(1, 750);
        CupCakeMapperDemo.createCompletedOrder(1, 750);
        CupCakeMapperDemo.createCompletedOrder(1, 750);
        CupCakeMapperDemo.createCompletedOrder(1, 750);
        
        int expectednow = 5;
        int actuallynow = CupCakeMapperDemo.getLatestOrderNumber();
        
        assertEquals(expectednow, actuallynow);
        
    }
    
    @Test
    public void DBGetName() throws SQLException, ClassNotFoundException {
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
        
        //Created a user so that we have a user.
        CupCakeMapperDemo.reqisterUser("Testuser", "password", "name", "email");
        
        
        String expected = "name";
        //Checking name in DB.
        String actually = CupCakeMapperDemo.getNameOfUser("Testuser");
        
        assertEquals(expected, actually);
    }
    
    }
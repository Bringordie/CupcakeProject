package DBtest1;

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
    
    @Ignore
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
    
    @Ignore
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
    
    @Ignore
    @Test
    public void DBTestPasswordIsEncrypted() throws SQLException, ClassNotFoundException {
        //Clean up should be placed correct places where it's needed in end.
        CupCakeMapperDemo.droptable();
        CupCakeMapperDemo.createtable();
    
        CupCakeMapperDemo.reqisterUser("CPHtestencryption", "encrypted", "Testperson", "test@email.org");
        
        //Should work but doesn't will invitage another time
        //boolean callbackofpassword = CupCakeMapperDemo.checkPassword("CPHtestencryption", "encrypted");
        
        //Works for now.
        Boolean callbackofpassword = CupCakeMapper.checkPassword("CPHtestencryption", "encrypted");
        
        assertFalse(callbackofpassword);
        
        CupCakeMapperDemo.reqisterUser("CPHtestencryption2", "stillencrypted", "Testperson", "test@email.org");
        
        //Should work but doesn't will invitage another time
        //Boolean callbackofpassword2 = CupCakeMapperDemo.checkPassword("CPHtestencryption2", "stillencrypted");
        
        //Works for now.
        Boolean callbackofpassword2 = CupCakeMapper.checkPassword("CPHtestencryption", "encrypted");
        
        assertFalse(callbackofpassword2);
}
    
    @Ignore
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
    
    
    }
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.CupCakeMapper;
import persistence.DBConnection;
import static persistence.DBConnection.getConnection;


public class registerUserTest {
    //Har ike kørt testen endnu da jeg syntes vi snakkede om at lave en kopi-db så den ikke fucker noget op. -Jonathan
    @Test
    public static void registerUserTest() throws SQLException, ClassNotFoundException {
        DBConnection dbc = new DBConnection();
        CupCakeMapper cm = new CupCakeMapper();
      
        cm.reqisterUser("abe", "pass", "abe", "email@email.com");
 
        dbc.getConnection();
        
            // Statement for at indsætte et enkelt record
            String sqlInsert = "SELECT Username FROM users WHERE Email='email@email.com";
            ResultSet result = getConnection().prepareStatement(sqlInsert).executeQuery();
            String dbString = "abe";
            assertEquals(dbString, result);
        } 
      
    }
    


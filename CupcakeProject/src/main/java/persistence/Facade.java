/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import logic.Bottom;
import logic.LineItems;
import logic.Topping;

/**
 *
 * @author Frederik
 */
public interface Facade {
    public void reqisterUser(String username, String password, String name, String email) throws SQLException, ClassNotFoundException;
    
    public boolean checkUsername(String username) throws ClassNotFoundException, SQLException;
    
    public boolean checkPassword(String username, String password) throws ClassNotFoundException, SQLException;
    
    public boolean checkRole(String username) throws ClassNotFoundException, SQLException;
    
    public ArrayList<Topping> getToppings() throws SQLException, ClassNotFoundException;
    
    public ArrayList<Bottom> getBottoms() throws SQLException, ClassNotFoundException;
    
    public void updateBalance(String username, double Addbalance) throws SQLException, ClassNotFoundException;
    
    public double getBalance(String username) throws SQLException, ClassNotFoundException;
    
    public int getLatestOrderNumber() throws SQLException, ClassNotFoundException;
    
    public void createCompletedOrder(int idUser, double totalprice) throws SQLException, ClassNotFoundException;
    
    public void createLineItems(ArrayList<LineItems> lineitems) throws SQLException, ClassNotFoundException;
    
    public String getNameOfUser(String username) throws SQLException, ClassNotFoundException;
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Bottom;
import logic.CupCake;
import logic.Shoppingcart;
import logic.Topping;
import persistence.CupCakeMapper;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class addCupCakeToShoppingCart extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, ClassNotFoundException{
        
        Topping toppingsnull = null;
        Bottom bottomsnull = null;
        
        String[] choicesbottom = request.getParameterValues("idbottom");
        String[] choicestopping = request.getParameterValues("idtopping");
       
        ArrayList<Topping> toppings =  CupCakeMapper.getToppings();
        ArrayList<Bottom> bottoms =  CupCakeMapper.getBottoms();

        int toppingchoice = 0;
        int bottomchoice = 0;
        double toppingprice = 0;
        double bottomprice = 0;
        String toppingname = "";
        String bottomname = "";
        
        String webpage = "Invoice";

        //Checking what toppings was selected
        if (choicestopping != null) {
            for (int i = 0; i < choicestopping.length; ++i) {
                int id = Integer.parseInt(choicestopping[i]);
                for (int j = 0; j < toppings.size(); ++j) {
                    toppingsnull = toppings.get(j);

                    if (toppingsnull.getId() == id) {
                        //choosenTopping.add(toppingsnull);
                        bottomprice += toppingsnull.getPrice();
                        toppingchoice = toppingsnull.getId();
                        toppingname = toppingsnull.getName();
                    }
                }
            }
            
            //Checking what bottoms was selected
            for (int i = 0; i < choicesbottom.length; ++i) {
                int id = Integer.parseInt(choicesbottom[i]);
                for (int j = 0; j < bottoms.size(); ++j) {
                    bottomsnull = bottoms.get(j);

                    if (bottomsnull.getId() == id) {
                        //choosenBottom.add(bottomsnull);
                        bottomprice += bottomsnull.getPrice();
                        bottomchoice = bottomsnull.getId();
                        bottomname = bottomsnull.getName();
                    }
                }
            }
            double sum = toppingprice + bottomprice;
            Shoppingcart.setTotalprice((Math.round(sum * 100) / 100.0));
            Topping toppingadded = new Topping(toppingname, toppingprice, toppingchoice);
            Bottom bottomadded = new Bottom(bottomname, bottomprice, bottomchoice);
            CupCake cupcake = new CupCake(toppingadded, bottomadded, Shoppingcart.getTotalprice());
    }
     return webpage;
}
    }

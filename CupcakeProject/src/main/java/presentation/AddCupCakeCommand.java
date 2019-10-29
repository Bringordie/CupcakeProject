package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Bottom;
import logic.CupCake;
import logic.LineItems;
import logic.Shoppingcart;
import logic.Topping;


/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class AddCupCakeCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        Topping toppingsnull = null;
        Bottom bottomsnull = null;

        String[] choicesbottom = request.getParameterValues("idbottom");
        String[] choicestopping = request.getParameterValues("idtopping");
        int quantitychoosen = Integer.parseInt(request.getParameter("quantity"));

        ArrayList<Topping> toppings = db.getToppings();
        ArrayList<Bottom> bottoms = db.getBottoms();

        int toppingchoice = 0;
        int bottomchoice = 0;
        double toppingprice = 0;
        double bottomprice = 0;
        String toppingname = "";
        String bottomname = "";

        String webpage = "Products";

        //Checking what toppings was selected
        if (choicestopping != null) {
            for (int i = 0; i < choicestopping.length; ++i) {
                int id = Integer.parseInt(choicestopping[i]);
                for (int j = 0; j < toppings.size(); ++j) {
                    toppingsnull = toppings.get(j);

                    if (toppingsnull.getId() == id) {
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
                        bottomprice += bottomsnull.getPrice();
                        bottomchoice = bottomsnull.getId();
                        bottomname = bottomsnull.getName();
                    }
                }
            }
            double sum = toppingprice + bottomprice;
            Shoppingcart.setTotalprice((Math.round(sum * 100) / 100.0) * quantitychoosen);

            CupCake cupcake = new CupCake(toppingchoice, bottomchoice);
            LineItems lineitems = new LineItems(cupcake, quantitychoosen);
            Shoppingcart shoppingcart = new Shoppingcart();            
            shoppingcart.addToShoppingCart(lineitems);


        }
        return webpage;
    }    
}

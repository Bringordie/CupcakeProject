package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Bottom;
import logic.Topping;
import persistence.CupCakeMapper;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class goToProducts extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, ClassNotFoundException{
        ArrayList<Bottom> bottoms = CupCakeMapper.getBottoms();
        request.getSession().setAttribute("bottoms", bottoms);

        ArrayList<Topping> toppings = CupCakeMapper.getToppings();
        request.getSession().setAttribute("toppings", toppings);

        String webpage = "Products";
        return webpage;
    }
    
}

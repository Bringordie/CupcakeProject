package presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;


/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class AddBalanceCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, ClassNotFoundException{
        double balanceToAdd = Double.parseDouble(request.getParameter("AddBalance"));

        User getUsername = new User();
        String username4 = (String) getUsername.getUsername();
        db.updateBalance(username4, balanceToAdd);

        String webpage = "CustomerPage";
        return webpage;
    }
    
}

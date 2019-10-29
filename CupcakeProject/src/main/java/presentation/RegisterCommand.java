package presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class RegisterCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, ClassNotFoundException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        Boolean usernameDB = db.checkUsername(username);
        String WebPage = "";

        if (usernameDB == false) {
            db.reqisterUser(username, password, name, email);
            request.getSession().setAttribute("userloggedinname", name);
            WebPage = "CustomerPage";
            return WebPage;
        } else {
            return "RegistrationError";
        }
    }
}

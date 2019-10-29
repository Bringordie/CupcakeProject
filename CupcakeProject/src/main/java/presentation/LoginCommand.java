package presentation;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, ClassNotFoundException{
        String username = request.getParameter("usernamelogin");
        String password = request.getParameter("passwordlogin");
        Boolean usernameDB = db.checkUsername(username);
        Boolean passwordDB = db.checkPassword(username, password);
        Boolean getRole = db.checkRole(username);

        logic.User saveUsername = new logic.User();
        saveUsername.setUsername(username);
        
        String WebPage = "";

        if (usernameDB && passwordDB == true && getRole == true) {
            WebPage = "AdminPage";
        } else if (usernameDB && passwordDB == true && getRole == false) {
            String getUsername = db.getNameOfUser(username);
            
        request.getSession().setAttribute("userloggedinname", getUsername);
            WebPage = "CustomerPage";
        } else {
            //Wrong password or username
            //Test example?
            WebPage = "LoginError";
        }
        return WebPage;
    }

}

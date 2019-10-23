package presentation;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import persistence.CupCakeMapper;

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
        Boolean usernameDB = CupCakeMapper.checkUsername(username);
        Boolean passwordDB = CupCakeMapper.checkPassword(username, password);
        Boolean getRole = CupCakeMapper.checkRole(username);

        logic.User saveUsername = new logic.User();
        saveUsername.setUsername(username);
        
        String WebPage = "";

        if (usernameDB && passwordDB == true && getRole == true) {
            WebPage = "AdminPage";
        } else if (usernameDB && passwordDB == true && getRole == false) {
            WebPage = "CustomerPage";
        } else {
            //Wrong password or username
            //Test example?
            WebPage = "LoginError";
        }
        return WebPage;
    }

}

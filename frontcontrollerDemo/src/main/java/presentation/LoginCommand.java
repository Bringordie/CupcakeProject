package presentation;

import domainmodel.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domainmodel.LogicFacade;
import domainmodel.User;

/**
 * The purpose of LoginCommand is to initiate the registration process of a user
 * by invoking appropriate behavior in the domainmodel layer
 *
 * Pattern: Command
 *
 * @author tine
 */
public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        return user.getRole() + "page";
    }

}

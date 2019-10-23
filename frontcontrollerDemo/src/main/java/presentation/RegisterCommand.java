package presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domainmodel.LogicFacade;
import domainmodel.LoginException;
import domainmodel.User;

/**
 * The purpose of RegisterCommand is to initiate the registration process of a
 * user by invoking appropriate behavior in the domainmodel layer
 *
 * Pattern: Command
 *
 * @author tine
 */
public class RegisterCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals(password2)) {
            User user = LogicFacade.createUser(email, password1);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            return user.getRole() + "page";
        } else {
            throw new LoginException("the two passwords did not match");
        }
    }
}

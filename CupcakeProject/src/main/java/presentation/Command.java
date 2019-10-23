package presentation;

//import domainmodel.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("gotoRegisterUser", new goToRegisterCommand());
        commands.put("gotoLoginUser", new goToLoginCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegisterCommand());
        commands.put("goToProducts", new goToProducts());
        commands.put("addBalance", new addBalance());
        commands.put("payment", new addCupCakeToShoppingCart());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("cmd");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException;
}

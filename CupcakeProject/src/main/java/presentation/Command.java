package presentation;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.DBFacade;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public abstract class Command{
    DBFacade db = new DBFacade();

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("gotoRegisterUser", new GoToRegisterCommand());
        commands.put("gotoLoginUser", new GoToLoginCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegisterCommand());
        commands.put("goToProducts", new GoToProductsCommand());
        commands.put("addBalance", new AddBalanceCommand());
        commands.put("payment", new AddCupCakeCommand());
        commands.put("processorder", new GoToInvoiceCommand());
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

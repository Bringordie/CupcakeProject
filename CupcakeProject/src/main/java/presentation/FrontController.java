package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Bottom;
import logic.CupCake;
import logic.SHA256;
import logic.Shoppingcart;
import logic.Topping;
import logic.User;
import persistence.CupCakeMapper;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        switch (request.getParameter("cmd")) {
            case "registration":
                userRegistration(request, response);
                break;
            case "gotoRegisterUser":
                goToUserRegistration(request, response);
                break;
            case "gotoLoginUser":
                goToLoginUser(request, response);
                break;
            case "login":
                userLogin(request, response);
                break;
            case "goToProducts":
                goToProducts(request, response);
                break;
            case "addBalance":
                addBalance(request, response);
                break;
            case "payment":
                addCupCakeToShoppingCart(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void userRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Boolean usernameDB = CupCakeMapper.checkUsername(username);

        if (usernameDB == false) {
            CupCakeMapper.reqisterUser(username, password, name, email);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\CustomerPage.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("RegistrationError.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void userLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String username = request.getParameter("usernamelogin");
        String password = request.getParameter("passwordlogin");
        Boolean usernameDB = CupCakeMapper.checkUsername(username);
        Boolean passwordDB = CupCakeMapper.checkPassword(username, password);
        Boolean getRole = CupCakeMapper.checkRole(username);

        User saveUsername = new User();
        saveUsername.setUsername(username);
        //User user = new User("","","","");
        //user.setUsername(username);

        if (usernameDB && passwordDB == true && getRole == true) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\AdminPage.jsp");
            rd.forward(request, response);
        } else if (usernameDB && passwordDB == true && getRole == false) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\CustomerPage.jsp");
            rd.forward(request, response);
        } else {
            //Wrong password or username
            //Test example?
            RequestDispatcher rd = request.getRequestDispatcher("LoginError.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void goToUserRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
        rd.forward(request, response);
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void goToLoginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void goToProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        ArrayList<Bottom> bottoms = CupCakeMapper.getBottoms();
        request.getSession().setAttribute("bottoms", bottoms);

        ArrayList<Topping> toppings = CupCakeMapper.getToppings();
        request.getSession().setAttribute("toppings", toppings);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\Products.jsp");
        rd.forward(request, response);
    }

    /**
     * @author Bringordie - Frederik Braagaard
     */
    public void addBalance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        double balanceToAdd = Double.parseDouble(request.getParameter("AddBalance"));

        User getUsername = new User();
        String username4 = (String) getUsername.getUsername();
        CupCakeMapper.updateBalance(username4, balanceToAdd);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\CustomerPage.jsp");
        rd.forward(request, response);
    }

    
    //Works 99%. TODO. See/Fix how to grab the total ammount correct, somehow only one of them holds the total?
    public void addCupCakeToShoppingCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        Topping toppingsnull = null;
        Bottom bottomsnull = null;
        
        String[] choicesbottom = request.getParameterValues("idbottom");
        String[] choicestopping = request.getParameterValues("idtopping");
       
        ArrayList<Topping> toppings =  CupCakeMapper.getToppings();
        ArrayList<Bottom> bottoms =  CupCakeMapper.getBottoms();

        //ArrayList<Topping> choosenTopping = new ArrayList();
        //ArrayList<Bottom> choosenBottom = new ArrayList();
        int toppingchoice = 0;
        int bottomchoice = 0;
        double toppingprice = 0;
        double bottomprice = 0;
        String toppingname = "";
        String bottomname = "";

        //Checking what toppings was selected
        if (choicestopping != null) {
            for (int i = 0; i < choicestopping.length; ++i) {
                int id = Integer.parseInt(choicestopping[i]);
                for (int j = 0; j < toppings.size(); ++j) {
                    toppingsnull = toppings.get(j);

                    if (toppingsnull.getId() == id) {
                        //choosenTopping.add(toppingsnull);
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
                        //choosenBottom.add(bottomsnull);
                        bottomprice += bottomsnull.getPrice();
                        bottomchoice = bottomsnull.getId();
                        bottomname = bottomsnull.getName();
                    }
                }
            }
            double sum = toppingprice + bottomprice;
            Shoppingcart.setTotalprice((Math.round(sum * 100) / 100.0));
            Topping toppingadded = new Topping(toppingname, toppingprice, toppingchoice);
            Bottom bottomadded = new Bottom(bottomname, bottomprice, bottomchoice);
            CupCake cupcake = new CupCake(toppingadded, bottomadded, Shoppingcart.getTotalprice());

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\InvoiceDetails.jsp");
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

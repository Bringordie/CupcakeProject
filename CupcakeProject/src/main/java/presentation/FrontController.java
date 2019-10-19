package presentation;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import logic.SHA256;
import logic.Topping;
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
            default:
                break;
        }
    }

    public void userRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String encrypt = SHA256.getHash(password.getBytes());
        CupCakeMapper.reqisterUser(username, encrypt);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\CustomerPage.jsp");
        rd.forward(request, response);
    }

    public void userLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        String username = request.getParameter("usernamelogin");
        String password = request.getParameter("passwordlogin");
        Boolean usernameDB = CupCakeMapper.checkUsername(username);
        Boolean passwordDB = CupCakeMapper.checkPassword(username, password);
        Boolean getRole = CupCakeMapper.checkRole(username);
        if (usernameDB && passwordDB == true && getRole == true) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\AdminPage.jsp");
            rd.forward(request, response);
        } else if (usernameDB && passwordDB == true && getRole == false) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\CustomerPage.jsp");
            rd.forward(request, response);
        } else {
            //Wrong password or username
            //Test example
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
    }

    public void goToUserRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
        rd.forward(request, response);
    }

    public void goToLoginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }
    
    public void goToProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        ArrayList<Bottom> bottoms = CupCakeMapper.getButtoms();
        request.getSession().setAttribute("bottoms", bottoms);
        
        ArrayList<Topping> toppings = CupCakeMapper.getToppings();
        request.getSession().setAttribute("toppings", toppings);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF\\Products.jsp");
        rd.forward(request, response);
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

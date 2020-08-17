package units.progettotomcat.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author massi
 */
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SERVLET DOESN'T ACCEPT GET REQUESTS
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("user")) {

            case "consumer":
                request.getRequestDispatcher("AddConsumer").forward(request, response);
                break;
            case "uploader":
                request.getRequestDispatcher("AddUploader").forward(request, response);
                break;
            case "administrator":
                request.getRequestDispatcher("AddAdministrator").forward(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

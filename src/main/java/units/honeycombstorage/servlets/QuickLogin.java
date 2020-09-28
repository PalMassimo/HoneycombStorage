package units.honeycombstorage.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.honeycombstorage.entites.Consumer;

/**
 *
 * @author massi
 */
@WebServlet(name = "QuickLogin", urlPatterns = {"/quicklogin"})
public class QuickLogin extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(405, "Method Not Allowed");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //EntityManagerFactory emf=Persistence.createEntityManagerFactory("deploymentPU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
        EntityManager em = emf.createEntityManager();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (em.find(Consumer.class, username) != null) {
            if ((em.find(Consumer.class, username).getPassword()).equals(password)) {
                request.getSession().setAttribute("role", "consumer");
                request.getSession().setAttribute("username", username);
                response.sendRedirect("api/consumerarea/file/" + request.getParameter("id") + "/" + request.getParameter("filename"));
            } else {
                response.sendError(422, "the password is wrong");
            }
        } else {
            response.sendError(401, "consumer doesn't exist");
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Quick Login Servlet";
    }
    
}

package units.honeycombstorage.servlets;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.honeycombstorage.entities.storage.Consumer;

/**
 *
 * @author massi
 */
@WebServlet(name = "QuickLogin", urlPatterns = {"/quicklogin"})
public class QuickLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(405, "Method Not Allowed");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //get username and password from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String idFile = request.getParameter("id");
        String fileName = request.getParameter("filename");

        if (username == null || password == null || idFile == null || fileName == null) {
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
        EntityManager em = emf.createEntityManager();

        //check if user exist and if the credentials are right
        if (em.find(Consumer.class, username) != null) {
            if ((em.find(Consumer.class, username).getPassword()).equals(password)) {
                request.getSession().setAttribute("role", "consumer");
                request.getSession().setAttribute("username", username);
                response.sendRedirect("api/consumerarea/file/" + idFile + "/" + fileName);
            } else {
                response.sendError(422, "wrong password");
            }
        } else {
            response.sendError(401, "consumer doesn't exist");
        }
        em.close();
        emf.close();

    }

    @Override
    public String getServletInfo() {
        return "Quick Login Servlet";
    }

}

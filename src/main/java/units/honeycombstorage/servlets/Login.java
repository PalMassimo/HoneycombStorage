package units.honeycombstorage.servlets;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.honeycombstorage.entities.storage.Administrator;
import units.honeycombstorage.entities.storage.Consumer;
import units.honeycombstorage.entities.storage.Uploader;

/**
 *
 * @author massi
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //servlet doesn't accept get requests
        response.sendRedirect("/login.html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username == null || password == null || role == null) {
            response.sendRedirect("login.html");
            return;
        }

        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
        EntityManager em = emf.createEntityManager();

        //search in database based on entity type
        switch (role) {

            case "consumer":
                if (em.find(Consumer.class, username) != null) {
                    if ((em.find(Consumer.class, username).getPassword()).equals(password)) {
                        request.getSession().setAttribute("role", role);
                        request.getSession().setAttribute("username", username);
                        response.sendRedirect("consumersrealm/index.html");
                    } else {
                        response.sendError(422, "wrong password");
                    }
                } else {
                    response.sendError(401, "consumer doesn't exist");
                }
                break;

            case "uploader":
                if (em.find(Uploader.class, username) != null) {
                    if (em.find(Uploader.class, username).getPassword().equals(password)) {
                        request.getSession().setAttribute("role", role);
                        request.getSession().setAttribute("username", username);
                        response.sendRedirect("uploadersrealm/index.html");
                    } else {
                        response.sendError(422, "wrong password");
                    }
                } else {
                    response.sendError(401, "uploader doesn't exist");
                }
                break;

            case "administrator":
                if (em.find(Administrator.class, username) != null) {
                    if (em.find(Administrator.class, username).getPassword().equals(password)) {
                        request.getSession().setAttribute("username", username);
                        request.getSession().setAttribute("role", role);
                        response.sendRedirect("administratorsrealm/index.html");
                    } else {
                        response.sendError(422, "wrong password");
                    }
                } else {
                    response.sendError(401, "administrator doesn't exist");
                }
                break;

            default:
                break;
        }
        em.close();
        emf.close();

    }

    @Override
    public String getServletInfo() {
        return "login users";
    }
}

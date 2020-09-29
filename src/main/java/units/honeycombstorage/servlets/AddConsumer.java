package units.honeycombstorage.servlets;

import java.io.IOException;
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
@WebServlet(name = "AddConsumer", urlPatterns = {"/addconsumer"})
public class AddConsumer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SERVLET DOESN'T ACCEPT GET REQUESTS
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //check if all the parameters are not null
        if (request.getParameter("username") == null || request.getParameter("email") == null
                || request.getParameter("namesurname") == null || request.getParameter("password") == null) {
            response.sendRedirect("subscribe.html");
        } else {
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
            EntityManager em = emf.createEntityManager();

            //verify if the username is already taken
            if (em.find(Consumer.class, request.getParameter("username")) != null) {
                response.sendRedirect("login.html");
            } else {
                //let's create the new consumer
                Consumer consumer = new Consumer();
                consumer.setUsername(request.getParameter("username"));
                consumer.setEmail(request.getParameter("email"));
                consumer.setNameSurname(request.getParameter("namesurname"));
                consumer.setPassword(request.getParameter("password"));
                em.getTransaction().begin();
                em.persist(consumer);
                em.getTransaction().commit();
                request.getSession().setAttribute("username", consumer.getUsername());
                request.getSession().setAttribute("role", "consumer");
                response.sendRedirect("consumersrealm/index.html");
            }

            em.close();
            emf.close();
        }

    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding new consumer";
    }
}

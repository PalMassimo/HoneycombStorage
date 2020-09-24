package units.progettotomcat.servlets;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.progettotomcat.entites.Consumer;

/**
 *
 * @author massi
 */
@WebServlet(name = "AddConsumer", urlPatterns = {"/addconsumer"})
public class AddConsumer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SERVLET DOESN'T ACCEPT GET REQUEST
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("username") == null || request.getParameter("email") == null || request.getParameter("nomecognome") == null || request.getParameter("password") == null) {
            response.sendRedirect("subscribe.html");
        }

        //let's create the new consumer
        Consumer consumer = new Consumer();
        consumer.setUsername(request.getParameter("username"));
        consumer.setEmail(request.getParameter("email"));
        consumer.setNameSurname(request.getParameter("namesurname"));
        consumer.setPassword(request.getParameter("password"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
        EntityManager em = emf.createEntityManager();

        //verify that the username is already taken
        if (em.find(Consumer.class, request.getParameter("username")) == null) {
            response.sendRedirect(request.getHeader("referer"));
        } else{
            em.getTransaction().begin();
            em.persist(consumer);
            em.getTransaction().commit();
            response.sendRedirect("consumersrealm/index.html");
        }

    }

    @Override
    public String getServletInfo() {
        return "Servlet per aggiungere un consumer";
    }
}

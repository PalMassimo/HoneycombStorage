package units.progettotomcat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.progettotomcat.entites.Consumer;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@WebServlet(name = "AddConsumer", urlPatterns = {"/AddConsumer"})
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

        Consumer consumer = new Consumer();
        consumer.setUsername(request.getParameter("username"));
        consumer.setEmail(request.getParameter("email"));
        consumer.setNomeCognome(request.getParameter("nomecognome"));
        consumer.setPassword(request.getParameter("password"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        //verify that consumer already exists
        TypedQuery<Consumer> query = em.createQuery("SELECT c FROM Consumer c", Consumer.class);
        List<Consumer> consumers = query.getResultList();
        boolean exist = false;

        for (Consumer oldConsumer : consumers) {
            if ((oldConsumer.getUsername()).equals(request.getParameter("username"))) {
                exist = true;
                break;
            }
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddConsumer</title>");
            out.println("</head>");
            out.println("<body>");

            //Who does the request? A new consumer, an uploader or an administrator?
            //...we can check it by where the request is sent
            if ((String) request.getSession().getAttribute("role") != null) {
                switch ((String) request.getSession().getAttribute("role")) {

                    //an admin wants to create consumer
                    case "administrator":
                        break;

                    //an uploader wants to create consumer    
                    case "uploader":
                        //add the uploader to the uploaders list of the new consumer
                        consumer.getUploaders().add(em.find(Uploader.class, (String) request.getSession().getAttribute("username")));
                        //add to the consumer list of the uploader a new consumer
                        (em.find(Uploader.class, (String) request.getSession().getAttribute("username"))).getConsumers().add(consumer);
                        break;

                    //a new consumer
                    default:
                        break;
                }

            } else {
                request.getSession().setAttribute("role", "consumer");
                request.getSession().setAttribute("username", request.getParameter("username"));
            }

            if (exist) {
                out.println("<h1>Lo username <em>" + request.getParameter("username") + "</em> è già stato preso</h1>");
                out.println("<p>Mi dispiace</p>");
                if (em.find(Consumer.class, (String) request.getSession().getAttribute("username")).getUploaders().isEmpty()) {
                    out.println("<p>è vuota</p>");
                }
            } else {
                em.getTransaction().begin();
                em.persist(consumer);
                em.getTransaction().commit();

                out.println("<h1>Done! You are now a consumer</h1>");
                out.println("<p>Il tuo ruolo è quello di " + request.getSession().getAttribute("role") + "</p>");
                out.println("<p>Il tuo username: " + request.getParameter("username") + "</p>");
                out.println("<p>Il tuo username: " + request.getSession().getAttribute("username") + "</p>");
                out.println("<p>La tua email: " + request.getParameter("email") + "</p>");
                out.println("<p>Il tuo nome e cognome: " + request.getParameter("nomecognome") + "</p>");
                out.println("<p>LA REGISTRAZIONE E' AVVENUTA A OPERA DI " + request.getSession().getAttribute("username") + "</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet per aggiungere un consumer";
    }
}

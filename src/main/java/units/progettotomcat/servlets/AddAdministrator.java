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
import units.progettotomcat.entites.Administrator;

/**
 *
 * @author massi
 */
@WebServlet(name = "AddAdministrator", urlPatterns = {"/AddAdministrator"})
public class AddAdministrator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SERVLET DOESN'T ACCEPT GET REQUEST
        response.sendRedirect("/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("username") == null || request.getParameter("email") == null || request.getParameter("nomecognome") == null || request.getParameter("password") == null) {
            response.sendRedirect("subscribe.html");
        }

        Administrator administrator = new Administrator(
                request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("nomecognome"),
                request.getParameter("password"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        //verify that administrator already exists
        TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a", Administrator.class);
        List<Administrator> administrators = query.getResultList();
        boolean exist = false;

        for (Administrator oldAdministrator : administrators) {
            if ((oldAdministrator.getUsername()).equals(request.getParameter("username"))) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            em.getTransaction().begin();
            em.persist(administrator);
            em.getTransaction().commit();
            request.getSession().setAttribute("role", "administrator");
            request.getSession().setAttribute("username", request.getParameter("username"));
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddAdministrator</title>");
            out.println("</head>");
            out.println("<body>");
            if (exist) {
                out.println("<h1>Lo username <em>"+request.getParameter("username")+"</em> è già stato preso</h1>");
                out.println("<p>Non è che ti sei già registrato?</p>");
            } else {
                out.println("<h1>Done! Now you are an administrator</h1>");
                out.println("<p>Il tuo ruolo è quello di "+request.getSession().getAttribute("role")+"</p>");
                out.println("<p>Il tuo username: " + request.getParameter("username") + "</p>");
                out.println("<p>Il tuo username: " + request.getSession().getAttribute("username") + "</p>");
                out.println("<p>La tua email: " + request.getParameter("email") + "</p>");
                out.println("<p>Il tuo nome e cognome: " + request.getParameter("nomecognome") + "</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding new administrators";
    }

}

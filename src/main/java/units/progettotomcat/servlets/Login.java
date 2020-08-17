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
import units.progettotomcat.entites.Consumer;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //NON SONO ACCETTATE RICHIESTE DI LOGIN CON IL METODO GET
        response.sendRedirect("/login.html");
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String role = null;
        boolean check = false;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();
        
        switch (request.getParameter("role")) {
            
            case "consumer":
                
                TypedQuery<Consumer> qc = em.createQuery("SELECT c FROM Consumer c", Consumer.class);
                List<Consumer> consumers = qc.getResultList();
                
                for (Consumer consumer : consumers) {
                    if ((consumer.getUsername()).equals(request.getParameter("username")) && (consumer.getPassword()).equals(request.getParameter("password"))) {
                        check = true;
                        role = "consumer";
                        break;
                    }
                }
                break;
            
            case "uploader":
                TypedQuery<Uploader> qu = em.createQuery("SELECT u FROM Uploader u", Uploader.class);
                List<Uploader> uploaders = qu.getResultList();
                
                for (Uploader uploader : uploaders) {
                    if ((uploader.getUsername()).equals(request.getParameter("username")) && (uploader.getPassword()).equals(request.getParameter("password"))) {
                        check = true;
                        role = "uploader";
                        break;
                    }
                }
                break;
            
            case "administrator":
                TypedQuery<Administrator> qa=em.createQuery("SELECT a FROM Administrator a", Administrator.class);
                List<Administrator> administrators=qa.getResultList();
                
                for(Administrator administrator: administrators){
                    if ((administrator.getUsername()).equals(request.getParameter("username")) && (administrator.getPassword()).equals(request.getParameter("password"))) {
                        check = true;
                        role = "administrator";
                        break;
                    }
                }
                break;
            default:
                break;
        }
        
        if (check) {
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("username", request.getParameter("username"));
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html");
        out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        
        if (check) {
            out.println("<h1>Benvenuto/a, "+request.getParameter("username")+"! </h1>");
            out.println("<p>Il tuo ruolo è " + request.getSession().getAttribute("role") + "</p>");
        } else {
            out.println("<h1>Login Failure</h1>");
            out.println("<p>Non sei stato trovato all'interno del database....");
            out.println("Forse hai un altro ruolo? Hai inserito " + request.getParameter("role") + "</p>");    
        }
        
       out.println("</body>");        
       out.println("</html>"); 
    }
    
    @Override
    public String getServletInfo() {
        return "Questa servlet si occupa del login degli utenti";
    }
}

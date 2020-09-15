package units.progettotomcat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.apache.commons.io.IOUtils.toByteArray;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@MultipartConfig
@WebServlet(name = "AddLogo", urlPatterns = {"/AddLogo"})
public class AddLogo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //SERVLET DOESN'T ACCEPT GET REQUEST
        response.sendRedirect("/WelcomeUploadersPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = (String) request.getSession().getAttribute("role");
        //if (((String) request.getSession().getAttribute("role")).equals("uploader")) {
        if (true) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            //Uploader uf = em.find(Uploader.class, request.getSession().getAttribute("username"));
            Uploader uf = em.find(Uploader.class, "Goro");
            uf.setLogo(toByteArray(request.getPart("logo").getInputStream()));
            em.persist(uf);
            em.getTransaction().commit();
            String referrer =request.getHeader("referer");
            response.sendRedirect(request.getHeader("referer"));

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AddAdministrator</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Immagine cambiata con successo!</h1>");
                //out.println('<img/>');
                out.println("</body>");
                out.println("</html>");
            }

        } else {

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AddAdministrator</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Non sei un uploader</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manage logo image of uploaders";
    }
}

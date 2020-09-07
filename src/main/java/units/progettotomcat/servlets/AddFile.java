package units.progettotomcat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import units.progettotomcat.entites.UploadedFile;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@MultipartConfig
@WebServlet(name = "AddFile", urlPatterns = {"/AddFile"})
public class AddFile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //SERVLET DOESN'T ACCEPT GET REQUESTS
        response.sendRedirect("UploadersRealm/AddFile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddFile</title>");
            out.println("</head>");
            out.println("<body>");

            if ((request.getSession().getAttribute("role")).equals("uploader")) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
                EntityManager em = emf.createEntityManager();

                UploadedFile uf = new UploadedFile();
                uf.setContent(toByteArray(request.getPart("fileToUpload").getInputStream()));
                uf.setName(request.getPart("fileToUpload").getSubmittedFileName());
                uf.setUploadDate(new Date());
                uf.setSeenDate(null);
                uf.setAddressIP(null);
                uf.setHashtags(null);
                uf.setUploader(em.find(Uploader.class, (String) (request.getSession().getAttribute("username"))));

                em.getTransaction().begin();
                em.persist(uf);
                em.getTransaction().commit();

                request.getSession().setAttribute("idFile", uf.getId());
                request.getRequestDispatcher("/SendEmails").forward(request, response);
                
                //in attesa di essere cancellato
                /*if ((qemails.getResultList()).isEmpty()) {
                    response.sendRedirect("UploadersRealm/UploaderHomepage.jsp");
                }*/
                
            } else {
                out.println("<h1>Non sei un uploader</h1>");
            }

            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    public String getServletInfo() {
        return "this servlet handles files addition";
    }
}

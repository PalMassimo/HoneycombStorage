package units.progettotomcat.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import units.progettotomcat.entites.UploadedFile;

/**
 *
 * @author massi
 */
@WebServlet(name = "LoadFiles", urlPatterns = {"/LoadFiles"})
public class LoadFiles extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UploadedFile uf = new UploadedFile();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();
        uf = em.find(UploadedFile.class, 2l);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadFiles</title>");
            out.println("</head>");
            out.println("<body>");
            if (uf == null) {
                out.println("<h1>File non trovato</h1>");
            } else {
                out.println("<h1>File trovato!</h1>");
                out.println("<p>" + uf.getName() + "</p>");
                File file = new File("C:\\Users\\massi\\OneDrive\\Desktop\\Cartella\\"+uf.getName());
                OutputStream os = new FileOutputStream(file);
                os.write(uf.getContent());
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

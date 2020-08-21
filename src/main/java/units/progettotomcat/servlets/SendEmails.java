package units.progettotomcat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import units.progettotomcat.entites.UploadedFile;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@WebServlet(name = "SendEmails", urlPatterns = {"/SendEmails"})
public class SendEmails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendEmails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Id file: " + request.getSession().getAttribute("idFile") + "</h1>");

            //retrieve emails address of consumers
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<String> qemails = em.createQuery("SELECT c.email FROM Consumer AS c INNER JOIN c.uploaders AS cu WHERE cu.username=:username", String.class);
            qemails.setParameter("username", (String) request.getSession().getAttribute("username"));
            List<String> addresseesAddress = qemails.getResultList();

            //retrieve email address of the uploader
            TypedQuery<String> qsenderAddress = em.createQuery("SELECT ufu.email FROM UploadedFile AS uf INNER JOIN uf.uploader AS ufu WHERE uf.id=:id ", String.class);
            qsenderAddress.setParameter("id", request.getSession().getAttribute("idFile"));
            String senderAddress = qsenderAddress.getSingleResult();

            //PER IL DEBUGGING CHI RICEVE SARò IO
            String host = "smtp.gmail.com"; //GESTIRE IN FUTURO

            // Setup mail server
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("progettoprogrammazioneweb@gmail.com", "tomcat2020");

                }
            });

            //Used to debug SMTP issues
            //DEGUB: CAMBIO SIA MITTENTE CHE DESTINARIO
            senderAddress = "progettoprogrammazioneweb@gmail.com";
            List<String> debugList = new ArrayList<String>();
            debugList.add("progettoprogrammazioneweb@gmail.com");
            addresseesAddress = debugList;

            session.setDebug(true);

            try {
                for (String s : addresseesAddress) {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(senderAddress));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
                    message.setSubject("New file uploaded by " + em.find(Uploader.class, request.getSession().getAttribute("username")).getNomecognome());
                    message.setText("The uploader " + em.find(Uploader.class, request.getSession().getAttribute("username")).getNomecognome()
                            + "has upload a new file \"" + em.find(UploadedFile.class, request.getSession().getAttribute("idFile")).getName() + "\" for you!\n"
                            + "This is the link where you can download " + "http://localhost:8080/ProgettoTomCat/api/filemanagment/getfile/"//CORREGGI PER IL DEPLOY
                            +request.getSession().getAttribute("idFile")+"/"+em.find(UploadedFile.class, request.getSession().getAttribute("idFile")).getName());
                    Transport.send(message);
                }
            } catch (MessagingException e) {
                System.out.println("AIUTOOOOOOOOOOOOOOOOOO " + e);
            }

            //print results, only for debugging
            for (String s : addresseesAddress) {
                out.println("<li>subject: " + s + "</li>");
            }

            out.println("<p> Sender: " + senderAddress + "</p>");
            out.println("<p>fine body</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

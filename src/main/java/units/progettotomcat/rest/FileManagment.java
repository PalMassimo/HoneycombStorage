package units.progettotomcat.rest;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import static org.apache.commons.io.IOUtils.toByteArray;
import units.progettotomcat.entites.UploadedFile;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@Path("/filemanagment")
public class FileManagment {

    //mettere qui @Context request e reponse? È possibile metterle come variabili globali teoricamente
    @GET
    @Path("/getfile/{id:}/{name:}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public byte[] getFile(@Context HttpServletRequest requestContext, @PathParam("id") long id, @PathParam("name") String name) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        //implementare authorization
        UploadedFile downloadFile = em.find(UploadedFile.class, id);

        downloadFile.setSeenDate(new Date());
        downloadFile.setAddressIP(requestContext.getRemoteAddr());

        em.getTransaction().begin();
        em.persist(downloadFile);
        em.getTransaction().commit();

        return downloadFile.getContent();
    }

    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA) //serve? sembra di no ma avrebbe senso metterla
    public void postFile(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException, ServletException {

        //verifica inutile? chi non è administrator o uploader non può essere qui. Gestire eventualmente solo gli administrator
        //if (request.getSession().getAttribute("role") != null && (request.getSession().getAttribute("role")).equals("uploader")) {
        if (true) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
            EntityManager em = emf.createEntityManager();

            UploadedFile uf = new UploadedFile();
            uf.setContent(toByteArray(request.getPart("fileToUpload").getInputStream()));
            if (request.getParameter("filename") != null) {
                uf.setName(request.getParameter("filename"));
            } else {
                uf.setName(request.getPart("fileToUpload").getSubmittedFileName());
            }

            uf.setUploadDate(new Date());
            uf.setSeenDate(null);
            uf.setAddressIP(null);
            uf.setHashtags(null);
            uf.setContentType(request.getContentType());
            //uf.setUploader(em.find(Uploader.class, (String) (request.getSession().getAttribute("username")))); //elimina commento finito lo sviluppo in vue
            uf.setUploader(em.find(Uploader.class, "Sherry")); //siamo in fase di sviluppo!!!
            em.getTransaction().begin();
            em.persist(uf);
            em.getTransaction().commit();

            Logger logger = Logger.getLogger("MY LOGGER");
            logger.info("--------------------------------------------------------------------------------------------------------------------------------");
            logger.info("--------------------------------------------------------------------------------------------------------------------------------");
            logger.info("Richiesta da: " + request.getRemoteAddr());
            logger.info("File name: " + request.getParameter("Marius"));
            logger.info("Paperino: " + request.getParameter("Paperino"));
            logger.info("GuerrieroMagico: " + request.getParameter("GuerrieroMagico"));
            logger.info("Anonimo: " + null);
            logger.info("--------------------------------------------------------------------------------------------------------------------------------");
            logger.info("--------------------------------------------------------------------------------------------------------------------------------");            
            
//            TaskQueue queue= new TaskQueue();
//            ThreadPoolExecutor
            request.getSession().setAttribute("idFile", uf.getId()); //che senso ha metterlo nella sessione? Piuttosto aggiungilo nella richiesta
            request.getRequestDispatcher("/SendEmails").forward(request, response);
        } else {
            response.sendRedirect("/ProgettoTomCat/UploadersRealm/UploaderHomepage.jsp");
        }

    }

    @DELETE
    @Path("/deletefile/{id:}")
    public String deleteFile(@PathParam("id") long id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        UploadedFile deleteFile = em.find(UploadedFile.class, id);
        Query deleteQuery = em.createQuery("DELETE FROM UploadedFile WHERE id=:id");
        deleteQuery.setParameter("id", id);
        deleteQuery.executeUpdate();
        em.getTransaction().commit();

        return "file eliminato con successo! Credo...";
    }
}

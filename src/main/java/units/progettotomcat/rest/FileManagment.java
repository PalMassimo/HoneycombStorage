package units.progettotomcat.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import units.progettotomcat.entites.UploadedFile;

/**
 *
 * @author massi
 */
@Path("/filemanagment")
public class FileManagment {

    @GET
    @Path("/getfile/{id:}/{name:}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public byte[] getFile(@PathParam("id") long id, @PathParam("name") String name) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        UploadedFile downloadFile = em.find(UploadedFile.class, id);

        return downloadFile.getContent();
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

        return "fatto!";
    }
}

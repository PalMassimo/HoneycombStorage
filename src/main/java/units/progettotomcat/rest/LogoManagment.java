package units.progettotomcat.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@Path("/logomanagment")
public class LogoManagment {

    @GET
    @Path("/getlogo")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public byte[] getLogo(@Context HttpServletRequest request) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
        EntityManager em = emf.createEntityManager();

        //DEPLOY
        if (request.getSession().getAttribute("username") == null) {
            return em.find(Uploader.class, "Sherry").getLogo();
        } else {
            return em.find(Uploader.class, request.getSession().getAttribute("username")).getLogo();
        }

    }
    
    /*implementa post per uploadare l'immagine di profilo*/
}

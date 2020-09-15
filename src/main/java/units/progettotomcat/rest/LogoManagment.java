package units.progettotomcat.rest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/getlogo")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public byte[] getLogo() {

        Uploader uploader=em.find(Uploader.class, "Goro");
        //DEPLOY
        //filtro deve assicurarsi che chi fa la richiesta sia un uploader o un administrator
        if (em.find(Uploader.class, uploader.getUsername()).getLogo() != null) {
            return em.find(Uploader.class, uploader.getUsername()).getLogo();
        } else {
            return null;
        }
    }

}

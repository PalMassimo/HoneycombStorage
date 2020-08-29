package units.progettotomcat.rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import units.progettotomcat.entites.Consumer;
import units.progettotomcat.entites.Uploader;


/**
 *
 * @author massi
 */
@Path("/uploaderarea")
public class UploaderAreaManager {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/getconsumers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Consumer> getConsumers() {

        //Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        Uploader uploader = em.find(Uploader.class, "Sherry"); //only for debug
        TypedQuery<Consumer> qconsumers = em.createQuery("SELECT c FROM Uploader u INNER JOIN u.consumers c "
                + "WHERE u.username= :currentuploader", Consumer.class);
        qconsumers.setParameter("currentuploader", (String) request.getSession().getAttribute("username"));

        return qconsumers.getResultList();
    }

    @GET
    @Path("/getinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfo() {

        //info: numero documenti caricati e numero consumers affiliati
        //Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        Uploader uploader = em.find(Uploader.class, "Sherry"); //only for debug

        TypedQuery<Long> qtotalConsumers = em.createQuery("SELECT COUNT(c) FROM Uploader u INNER JOIN u.consumers c"
                + " WHERE u.username= :currentuploader", Long.class);
        //qtotalConsumers.setParameter("currentuploader", request.getSession().getAttribute("username"));
        qtotalConsumers.setParameter("currentuploader", "Sherry");

        TypedQuery<Long> qtotalUploadedFile = em.createQuery("SELECT COUNT(uf) FROM UploadedFile uf INNER JOIN uf.uploader ufu"
                + " WHERE ufu.username= :currentuploader", Long.class);
        //qtotalUploadedFile.setParameter("currentuploader", request.getSession().getAttribute("username"));
        qtotalUploadedFile.setParameter("currentuploader", "Sherry");
        
        //create JSON object in which we put the info
        JSONObject info = new JSONObject();
        info.put("total_consumers", qtotalConsumers.getSingleResult());
        info.put("total_uploadedFiles", qtotalUploadedFile.getSingleResult());
        
        return info.toString();
        //return new JSONObject("{'aa':'bb'}");
    }

}

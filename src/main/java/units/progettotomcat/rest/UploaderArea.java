package units.progettotomcat.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.json.JSONArray;
import units.progettotomcat.entites.Consumer;
import units.progettotomcat.entites.DownloadFile;
import units.progettotomcat.entites.UploadedFile;
import units.progettotomcat.entites.Uploader;

/**
 *
 * @author massi
 */
@Path("/uploaderarea")
public class UploaderArea {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/info")
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
        info.put("total_files", qtotalUploadedFile.getSingleResult());
        info.put("role", "uploader"); //sistema
        info.put("username", "Sherry"); //sistema

        //return info;
        return info.toString();
    }

    @PUT
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uploader changeInfo(Uploader changes) {//eliminato un throw exception. Se si rompe qualcosa maledici questa azione....

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, changes.getUsername());
        uploader.setEmail(changes.getEmail());
        uploader.setNomeCognome(changes.getNomeCognome());
        uploader.setPassword(changes.getPassword());
        em.persist(uploader);
        em.getTransaction().commit();
        //i parametri posti a null non devono essere salvati nel database!!
        uploader.setLogo(null);
        //uploader.setConsumer(null); manca il metodo...
        uploader.setUploadedFiles(null);

        return uploader;
    }

    @GET
    @Path("/consumermanagment")
    @Produces(MediaType.APPLICATION_JSON)
    //returns the consumers affiliate info 
    public List<Consumer> getConsumers() {

        //che sia possibile screamare in base a dove viene effettuata la richiesta? Ci sono due pagine vue diverse che la chiamano
        //Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        Uploader uploader = em.find(Uploader.class, "Sherry"); //only for debug
        TypedQuery<Consumer> qconsumers = em.createQuery("SELECT c FROM Uploader u INNER JOIN u.consumers c "
                + "WHERE u.username= :currentuploader", Consumer.class);
        qconsumers.setParameter("currentuploader", "Sherry");/*(String) request.getSession().getAttribute("username"));*/

        return qconsumers.getResultList();
    }

    @PUT
    @Path("/consumermanagment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Consumer changeInfo(Consumer update) {

        em.getTransaction().begin();
        Consumer consumer = em.find(Consumer.class, update.getUsername());
        consumer.setEmail(update.getEmail());
        consumer.setNomeCognome(update.getNomeCognome());
        consumer.setPassword(update.getPassword());
        em.persist(consumer);
        em.getTransaction().commit();

        return consumer;
    }

    @DELETE
    @Path("/consumermanagment/{username:}")
    //delete a consumer
    public String deleteConsumer(@PathParam("username") String username) {

        em.getTransaction().begin();
        Consumer consumer = em.find(Consumer.class, username);
        Query deleteQuery = em.createQuery("DELETE FROM Consumer WHERE username=:username");
        deleteQuery.setParameter("username", username);
        deleteQuery.executeUpdate();
        em.getTransaction().commit();
        return "consumer eliminato con successo!";
    }

    //usata da qualcuno sta roba?
    @GET
    @Path("/uploadermanagment")
    @Produces(MediaType.APPLICATION_JSON)
    public Uploader getUploader() {

        Uploader uploader = em.find(Uploader.class, "Sherry");
        uploader.setLogo(null);
        uploader.setUploadedFiles(null);
        return uploader;
    }

    @GET
    @Path("/filemanagment")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFileInfo() {

        //SIAMO IN MODALITA' SVILUPPO
        TypedQuery<UploadedFile> quf = em.createQuery("SELECT uf FROM UploadedFile uf INNER JOIN uf.uploader ufu "
                + "WHERE ufu.username = 'Sherry'", UploadedFile.class);
        //quf.setParameter("currentuploader", request.getSession().getAttribute("username")); perché siamo in sviluppo ancora

        ArrayList<UploadedFile> results = new ArrayList<UploadedFile>();

        JSONArray jarray = new JSONArray();
        for (UploadedFile uf : quf.getResultList()) {
            uf.setUploader(null);
            JSONObject jobject = new JSONObject();
            jobject.put("id", uf.getId());
            jobject.put("name", uf.getName());
            if (uf.getContent() == null) {
                jobject.put("size", 0);
            } else {
                jobject.put("size", uf.getContent().length);
            }
            jobject.put("hashtags", uf.getHashtags());
            jobject.put("uploadDate", uf.getUploadDate());
            jarray.put(jobject);
        }

        return jarray.toString();
    }

    @POST
    @Path("/filemanagment")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postFile(@QueryParam("consumers") String consumers, UploadedFile uploadedFile) {

        uploadedFile.setAddressIP(request.getRemoteAddr());
        uploadedFile.setUploader(em.find(Uploader.class, "Sherry")); //phase sviluppo
        uploadedFile.setUploadDate(new Date());
        em.getTransaction().begin();
        em.persist(uploadedFile);
        em.getTransaction().commit();

        String[] arrayConsumers = consumers.split(" ");

        for (String consumer : arrayConsumers) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setDownloaded(null);
            downloadFile.setConsumer(em.find(Consumer.class, consumer));
            downloadFile.setUploadedFile(uploadedFile);
            em.getTransaction().begin();
            em.persist(downloadFile);
            em.getTransaction().commit();

        }

        //response.sendRedirect(request.getHeader("referer"));
    }

    @DELETE
    @Path("/filemanagment/{id:}")
    public String deleteFile(@PathParam("id") long id) {

        //gestisci se il file non esiste
        em.getTransaction().begin();
        UploadedFile uf = em.find(UploadedFile.class, id);
        //Query deleteQuery = em.createQuery("DELETE FROM UploadedFile uf WHERE uf.id=:id");
        //deleteQuery.setParameter("id", id);
        //deleteQuery.executeUpdate();
        em.remove(em.find(UploadedFile.class, id));
        em.getTransaction().commit();
        em.close();
        return "Eliminazione effettuata!";
    }

}

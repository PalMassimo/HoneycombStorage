package units.honeycombstorage.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import static org.apache.commons.io.IOUtils.toByteArray;
import org.json.JSONObject;
import org.json.JSONArray;
import units.honeycombstorage.entites.Consumer;
import units.honeycombstorage.entites.DownloadFile;
import units.honeycombstorage.entites.UploadedFile;
import units.honeycombstorage.entites.Uploader;
import units.honeycombstorage.classes.MailSender;

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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/generalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGeneralInfo() {

        //info: numero documenti caricati e numero consumers affiliati
        //Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        Uploader uploader = em.find(Uploader.class, "Sherry"); //only for debug

        TypedQuery<Long> qtotalConsumers = em.createQuery("SELECT COUNT(c) FROM Uploader u INNER JOIN u.consumers c"
                + " WHERE u.username= :currentuploader", Long.class);
        qtotalConsumers.setParameter("currentuploader", uploader.getUsername());

        TypedQuery<Long> qtotalUploadedFile = em.createQuery("SELECT COUNT(uf) FROM UploadedFile uf INNER JOIN uf.uploader ufu"
                + " WHERE ufu.username= :currentuploader", Long.class);
        qtotalUploadedFile.setParameter("currentuploader", uploader.getUsername());

        //create JSON object in which we put the info
        JSONObject info = new JSONObject();
        info.put("total_consumers", qtotalConsumers.getSingleResult());
        info.put("total_files", qtotalUploadedFile.getSingleResult());
        info.put("role", "uploader"); //sistema
        info.put("username", uploader.getUsername()); //sistema

        return info.toString();
    }

    @GET
    @Path("/uploader")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUploader() {

        Uploader uploader = em.find(Uploader.class, "Sherry");
        JSONObject uploaderJSON = new JSONObject();
        uploaderJSON.put("username", uploader.getUsername());
        uploaderJSON.put("email", uploader.getEmail());
        uploaderJSON.put("nameSurname", uploader.getNameSurname());
        uploaderJSON.put("password", uploader.getPassword());

        return uploaderJSON.toString();
    }

    @PUT
    @Path("/uploader")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void modifyUploader(Uploader changes) throws IOException {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, changes.getUsername());
        uploader.setEmail(changes.getEmail());
        uploader.setNameSurname(changes.getNameSurname());
        uploader.setPassword(changes.getPassword());
        em.getTransaction().commit();
        //response.sendRedirect(request.getHeader("referer")+"#/PrivateArea");

    }

    @GET
    @Path("/logo")
    public byte[] getLogo() {
        Uploader uploader = em.find(Uploader.class, "Sherry");
        if (em.find(Uploader.class, uploader.getUsername()).getLogo() != null) {
            return em.find(Uploader.class, uploader.getUsername()).getLogo();
        } else {
            return null;
        }
    }

    @POST
    @Path("/logo")
    public void postLogo() throws IOException, ServletException {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, "Sherry");
        uploader.setLogo(toByteArray(request.getPart("logo").getInputStream()));
        em.getTransaction().commit();
        response.sendRedirect(request.getHeader("referer") + "#/PrivateArea");
    }

    @GET
    @Path("/consumers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Consumer> getConsumers() {
        //utilizzate da due vuejs
        Uploader uploader = em.find(Uploader.class, "Sherry");
        TypedQuery<Consumer> qconsumers = em.createQuery("SELECT c FROM Consumer c INNER JOIN c.uploaders cu "
                + "WHERE cu.username= :currentuploader ", Consumer.class);
        qconsumers.setParameter("currentuploader", uploader.getUsername());
        List<Consumer> consumers = qconsumers.getResultList();

        for (Consumer consumer : consumers) {
            consumer.setUploaders(null);
            consumer.setDownloadFiles(null);
        }
        return consumers;
    }

    @POST
    @Path("/consumer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postConsumer(Consumer consumer) throws IOException {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, "Sherry");
        if (em.find(Consumer.class, consumer.getUsername()) == null) {
            consumer.addUploader(uploader);
            em.persist(consumer);
        } else {
            response.sendError(409);
        }
        em.getTransaction().commit();
    }

    @PUT
    @Path("/consumer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeInfo(Consumer update) {

        em.getTransaction().begin();
        Consumer consumer = em.find(Consumer.class, update.getUsername());
        consumer.setEmail(update.getEmail());
        consumer.setNameSurname(update.getNameSurname());
        consumer.setPassword(update.getPassword());
        em.getTransaction().commit();
    }

    @DELETE
    @Path("/consumer/{username:}")
    public void deleteConsumer(@PathParam("username") String username) throws IOException {

        em.getTransaction().begin();
        em.remove(em.find(Consumer.class, username));
        em.getTransaction().commit();

    }

    @GET
    @Path("/consumersextended")
    @Produces(MediaType.APPLICATION_JSON)
    public String getConsumersExtended() {

        Uploader uploader = em.find(Uploader.class, "Sherry");
        JSONArray consumersJSONArray = new JSONArray();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);

        //take all the affiliate consumers
        TypedQuery<Consumer> consumersQuery = em.createQuery("SELECT c FROM Consumer c INNER JOIN c.uploaders cu "
                + "WHERE cu.username=:currentuploader", Consumer.class);
        consumersQuery.setParameter("currentuploader", uploader.getUsername());

        for (Consumer consumer : consumersQuery.getResultList()) {

            JSONObject consumerJSON = new JSONObject();
            consumerJSON.put("username", consumer.getUsername());
            JSONArray filesJSONArray = new JSONArray();
            //dobbiamo restituire solo i files caricati dall'uploader
            TypedQuery<DownloadFile> downloadFileQuery = em.createQuery("SELECT df "
                    + "FROM DownloadFile df INNER JOIN df.uploadedFile uf INNER JOIN uf.uploader "
                    + "WHERE uf.uploader=:currentuploader AND df.consumer=:currentconsumer", DownloadFile.class);
            downloadFileQuery.setParameter("currentuploader", uploader);
            downloadFileQuery.setParameter("currentconsumer", consumer);

            for (DownloadFile downloadfile : downloadFileQuery.getResultList()) {
                UploadedFile uf = downloadfile.getUploadedFile();
                JSONObject uploadedFileJSON = new JSONObject();
                uploadedFileJSON.put("id", uf.getId());
                uploadedFileJSON.put("name", uf.getName());
                if (downloadfile.getDownloaded() == null) {
                    uploadedFileJSON.put("seen", "");
                    uploadedFileJSON.put("ipAddress", "");
                } else {
                    uploadedFileJSON.put("seen", formatter.format(downloadfile.getDownloaded()));
                    uploadedFileJSON.put("ipAddress", downloadfile.getIpAddress());
                }
                //JSONArray hashtags = new JSONArray();

                filesJSONArray.put(uploadedFileJSON);
            }

            consumerJSON.put("files", filesJSONArray);
            consumersJSONArray.put(consumerJSON);
        }
        return consumersJSONArray.toString();

//        Uploader uploader = em.find(Uploader.class, "Sherry");
//        JSONObject json = new JSONObject();
//        TypedQuery<Consumer> consumersQuery = em.createQuery("SELECT c FROM Consumer c INNER JOIN c.uploaders cu "
//                + "WHERE cu.username=:currentuploader", Consumer.class);
//        consumersQuery.setParameter("currentuploader", uploader.getUsername());
//
//        for (Consumer consumer : consumersQuery.getResultList()) {
//
//            JSONArray uploadedFilesJSON = new JSONArray();
//            //dobbiamo restituire solo i files caricati dall'uploader
//            TypedQuery<DownloadFile> downloadFileQuery = em.createQuery("SELECT df "+
//                    "FROM DownloadFile df INNER JOIN df.uploadedFile uf INNER JOIN uf.uploader "+
//                    "WHERE uf.uploader=:currentuploader", DownloadFile.class);
//            downloadFileQuery.setParameter("currentuploader", uploader);
//            
//            //for (DownloadFile downloadfile : consumer.getDownloadFiles()) {
//            for (DownloadFile downloadfile : downloadFileQuery.getResultList()) {
//                UploadedFile uf = downloadfile.getUploadedFile();
//                JSONObject uploadedFileJSON = new JSONObject();
//                uploadedFileJSON.put("id", uf.getId());
//                uploadedFileJSON.put("name", uf.getName());
//                if (downloadfile.getDownloaded() == null) {
//                    uploadedFileJSON.put("seen", "");
//                    uploadedFileJSON.put("ipAddress", "");
//                } else {
//                    uploadedFileJSON.put("seen", downloadfile.getDownloaded().toString());
//                    uploadedFileJSON.put("ipAddress", downloadfile.getIpAddress());
//                }
//                JSONArray hashtags = new JSONArray();
//
//                uploadedFilesJSON.put(uploadedFileJSON);
//            }
//
//            json.put(consumer.getUsername(), uploadedFilesJSON);
//        }
//        return json.toString();
    }

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFileInfo() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        //SIAMO IN MODALITA' SVILUPPO
        Uploader uploader = em.find(Uploader.class, "Sherry");
        TypedQuery<UploadedFile> quf = em.createQuery("SELECT uf FROM UploadedFile uf INNER JOIN uf.uploader ufu "
                + "WHERE ufu.username =:currentuploader", UploadedFile.class
        );
        quf.setParameter("currentuploader", uploader.getUsername());

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
            jobject.put("uploadDate", formatter.format(uf.getUploadDate()));
            jarray.put(jobject);
        }

        return jarray.toString();
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.TEXT_PLAIN)
    public void uploadFile(String fileString) {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, "Sherry");
        JSONObject json = new JSONObject(fileString);
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setName(json.getString("name"));
        uploadedFile.setContent(Base64.getDecoder().decode(json.getString("content")));
        uploadedFile.setUploadDate(new Date());
        em.getTransaction().commit();

        JSONArray hashtagsJSONArray = json.getJSONArray("hashtags");
        String[] hashtags = new String[hashtagsJSONArray.length()];
        for (int i = 0; i < hashtags.length; i++) {
            hashtags[i] = hashtagsJSONArray.getString(i);
        }
        uploadedFile.setHashtags(hashtags);

        uploadedFile.setUploader(uploader);

        JSONArray consumersJSONArray = json.getJSONArray("consumers");
        for (int i = 0; i < consumersJSONArray.length(); i++) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setUploadedFile(uploadedFile);
            if (em.find(Consumer.class, consumersJSONArray.getJSONObject(i).getString("username")) != null) {
                downloadFile.setConsumer(em.find(Consumer.class, consumersJSONArray.getJSONObject(i).getString("username")));
            } else {
                Consumer consumer = new Consumer();
                consumer.setUsername(consumersJSONArray.getJSONObject(i).getString("username"));
                consumer.setEmail(consumersJSONArray.getJSONObject(i).getString("email"));
                consumer.setNameSurname(consumersJSONArray.getJSONObject(i).getString("nameSurname"));
                consumer.setPassword("password");
                consumer.addUploader(uploader);
                downloadFile.setConsumer(consumer);
                em.getTransaction().begin();
                em.persist(consumer);
                em.getTransaction().commit();
            }

            em.getTransaction().begin();
            em.persist(downloadFile);
            em.getTransaction().commit();

            //sending emails
            MailSender mailservlet = new MailSender();
            mailservlet.setDestinatario(consumersJSONArray.getJSONObject(i).getString("email"));
            mailservlet.setUploaderUsername(uploader.getUsername());
            mailservlet.setConsumerUsername(consumersJSONArray.getJSONObject(i).getString("username"));
            mailservlet.setId(uploadedFile.getId());
            mailservlet.setFilename(uploadedFile.getName());

            Thread thread = new Thread(mailservlet);
            thread.start();

        }
    }

    @DELETE
    @Path("/file/{id:}")
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

    ////////////////////////////////////////////////////
//    //usata da qualcuno sta roba?
//    @GET
//    @Path("/uploadermanagment")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Uploader getUploader() {
//
//        Uploader uploader = em.find(Uploader.class, "Goro");
//        uploader.setLogo(null);
//        uploader.setUploadedFiles(null);
//        return uploader;
//    }
//    @POST
//    @Path("/filemanagment")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void postFile(@QueryParam("consumers") String consumers, UploadedFile uploadedFile) {
//
//        Uploader uploader = em.find(Uploader.class, "Sherry");
//        uploadedFile.setUploader(uploader); //phase sviluppo
//        uploadedFile.setUploadDate(new Date());
//        em.getTransaction().begin();
//        em.persist(uploadedFile);
//        em.getTransaction().commit();
//
//        String[] arrayConsumers = consumers.split(" ");
//
//        for (String consumer : arrayConsumers) {
//            DownloadFile downloadFile = new DownloadFile();
//            downloadFile.setDownloaded(null);
//            if (em.find(Consumer.class, consumer) != null) {
//                downloadFile.setConsumer(em.find(Consumer.class, consumer));
//            } else {
//                Consumer newConsumer = new Consumer();
//            }
//
//            downloadFile.setUploadedFile(uploadedFile);
//            em.getTransaction().begin();
//            em.persist(downloadFile);
//            em.getTransaction().commit();
//
//        }
//
//        //response.sendRedirect(request.getHeader("referer"));
//    }
}
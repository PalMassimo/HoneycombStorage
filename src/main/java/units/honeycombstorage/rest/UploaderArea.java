package units.honeycombstorage.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import units.honeycombstorage.entities.storage.Consumer;
import units.honeycombstorage.entities.storage.DownloadFile;
import units.honeycombstorage.entities.storage.UploadedFile;
import units.honeycombstorage.entities.storage.Uploader;
import units.honeycombstorage.classes.MailSender;
import units.honeycombstorage.entities.rest.RestUploadedFile;

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

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/generalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGeneralInfo() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");

        //info: number of loaded files and affiliate consumers
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
        info.put("role", "uploader");
        info.put("username", uploader.getUsername());

        em.close();
        emf.close();
        return info.toString();
    }

    @GET
    @Path("/uploader")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUploader() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");

        JSONObject uploaderJSON = new JSONObject();
        uploaderJSON.put("username", uploader.getUsername());
        uploaderJSON.put("email", uploader.getEmail());
        uploaderJSON.put("nameSurname", uploader.getNameSurname());
        uploaderJSON.put("password", uploader.getPassword());

        em.close();
        emf.close();
        return uploaderJSON.toString();
    }

    @POST
    @Path("/uploader")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void modifyUploader(Uploader changes) {

        if (changes.getEmail() == null || changes.getNameSurname() == null || changes.getPassword() == null) {
            return;
        }

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
        uploader.setEmail(changes.getEmail());
        uploader.setNameSurname(changes.getNameSurname());
        uploader.setPassword(changes.getPassword());
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @GET
    @Path("/logo")
    public byte[] getLogo() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
        byte[] logo = em.find(Uploader.class, uploader.getUsername()).getLogo();
        em.close();
        emf.close();
        return logo;

    }

    @POST
    @Path("/logo")
    public void postLogo() throws ServletException, IOException {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
        uploader.setLogo(toByteArray(request.getPart("logo").getInputStream()));
        em.getTransaction().commit();
        response.sendRedirect(request.getHeader("referer"));
        em.close();
        emf.close();
    }

    @GET
    @Path("/consumers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Consumer> getConsumers() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");

        //used by two vuejs
        TypedQuery<Consumer> qconsumers = em.createQuery("SELECT c FROM Consumer c INNER JOIN c.uploaders cu "
                + "WHERE cu.username= :currentuploader ", Consumer.class);
        qconsumers.setParameter("currentuploader", uploader.getUsername());
        List<Consumer> consumers = qconsumers.getResultList();

        for (Consumer consumer : consumers) {
            consumer.setUploaders(null);
            consumer.setDownloadFiles(null);
        }
        em.close();
        emf.close();
        return consumers;
    }

    @POST
    @Path("/consumer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postConsumer(Consumer consumer) throws IOException {

        if (consumer.getUsername() == null || consumer.getEmail() == null || consumer.getNameSurname() == null
                || consumer.getPassword() == null) {
            return;
        }

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
        if (em.find(Consumer.class, consumer.getUsername()) == null) {
            consumer.addUploader(uploader);
            em.persist(consumer);
        } else {
            em.find(Consumer.class, consumer.getUsername()).addUploader(uploader);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @PUT
    @Path("/consumer/{username:}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeInfo(Consumer updates) {

        em.getTransaction().begin();
        Consumer consumer = em.find(Consumer.class, updates.getUsername());
        if (consumer != null) {
            consumer.setEmail(updates.getEmail());
            consumer.setNameSurname(updates.getNameSurname());
            consumer.setPassword(updates.getPassword());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @DELETE
    @Path("/consumer/{username:}")
    public void deleteConsumer(@PathParam("username") String username) throws IOException {

        //we remove the association, not the whole consumer entity
        em.getTransaction().begin();
        Consumer consumer = em.find(Consumer.class, username);
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));

        if (consumer != null) {
            consumer.removeUploader(uploader);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @GET
    @Path("/consumersextended")
    @Produces(MediaType.APPLICATION_JSON)
    public String getConsumersExtended() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
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

                filesJSONArray.put(uploadedFileJSON);
            }

            consumerJSON.put("files", filesJSONArray);
            consumersJSONArray.put(consumerJSON);
        }
        em.close();
        emf.close();
        return consumersJSONArray.toString();

    }

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RestUploadedFile> getFileInfo() {

        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        TypedQuery<Object[]> quf = em.createQuery("SELECT uf.id, uf.name, uf.uploadDate, length(uf.content)"
                + "FROM UploadedFile uf INNER JOIN uf.uploader ufu "
                + "WHERE ufu.username =:currentuploader", Object[].class);
        quf.setParameter("currentuploader", uploader.getUsername());

        ArrayList<RestUploadedFile> rufs = new ArrayList<>();

        for (Object[] uf : quf.getResultList()) {

            RestUploadedFile ruf = new RestUploadedFile();
            ruf.setId((long) uf[0]);
            ruf.setName((String) uf[1]);
            ruf.setUploadDate(formatter.format((Date) uf[2]));
            if (uf[3] == null) {
                ruf.setSize(0);
            } else {
                ruf.setSize((int) uf[3]);
            }

            rufs.add(ruf);
        }
        em.close();
        emf.close();
        return rufs;
    }

    @POST
    @Path("/file")
    @Consumes(MediaType.TEXT_PLAIN)
    public void uploadFile(String fileString) {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");
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
            em.getTransaction().begin();
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setUploadedFile(uploadedFile);
            if (em.find(Consumer.class, consumersJSONArray.getJSONObject(i).getString("username")) != null) {
                downloadFile.setConsumer(em.find(Consumer.class, consumersJSONArray.getJSONObject(i).getString("username")));
                em.find(Consumer.class, consumersJSONArray.getJSONObject(i).getString("username")).addUploader(uploader);
            } else {
                Consumer consumer = new Consumer();
                consumer.setUsername(consumersJSONArray.getJSONObject(i).getString("username"));
                consumer.setEmail(consumersJSONArray.getJSONObject(i).getString("email"));
                consumer.setNameSurname(consumersJSONArray.getJSONObject(i).getString("nameSurname"));
                consumer.setPassword("password");
                consumer.addUploader(uploader);
                downloadFile.setConsumer(consumer);
                em.persist(consumer);
            }
            em.persist(downloadFile);
            em.getTransaction().commit();

            //sending emails
            MailSender mailsender = new MailSender();
            mailsender.setDestinatario(consumersJSONArray.getJSONObject(i).getString("email"));
            mailsender.setUploaderUsername(uploader.getUsername());
            mailsender.setConsumerNameSurname(consumersJSONArray.getJSONObject(i).getString("nameSurname"));
            mailsender.setId(uploadedFile.getId());
            mailsender.setFilename(uploadedFile.getName());

            Thread thread = new Thread(mailsender);
            thread.start();
        }
        em.close();
        emf.close();
    }

    @DELETE
    @Path("/file/{id:[0-9]+}")
    public void deleteFile(@PathParam("id") long id) throws IOException {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, (String) request.getSession().getAttribute("username"));
        //Uploader uploader = em.find(Uploader.class, "Sherry");

        // catch the file the uploader wants to delete
        UploadedFile uploadedFile = em.find(UploadedFile.class, id);

        //an uploader can't delete file of another uploader
        if (uploadedFile != null && (uploadedFile.getUploader().getUsername()).equals(uploader.getUsername())) {
            em.remove(uploadedFile);
        } else {
            response.sendError(404, "file doesn't exist");
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}

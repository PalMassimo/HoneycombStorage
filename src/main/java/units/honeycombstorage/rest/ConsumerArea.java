package units.honeycombstorage.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import units.honeycombstorage.entities.rest.RestConsumer;
import units.honeycombstorage.entities.storage.Consumer;
import units.honeycombstorage.entities.storage.DownloadFile;
import units.honeycombstorage.entities.storage.UploadedFile;
import units.honeycombstorage.entities.storage.Uploader;

/**
 *
 * @author massi
 */
@Path("/consumerarea")
public class ConsumerArea {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("consumernews")
    @Produces(MediaType.APPLICATION_JSON)
    public String getConsumerNews() {

        // all the information about the consumer homepage and subpages,
        // except the private area
        JSONArray consumerNews = new JSONArray();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);

        Consumer consumer = em.find(Consumer.class, (String) request.getSession().getAttribute("username"));
        //Consumer consumer = em.find(Consumer.class, "RSSMRA80B27F205P");

        TypedQuery<Uploader> uploadersQuery = em.createQuery("SELECT u "
                + "FROM Consumer c INNER JOIN c.uploaders u "
                + "WHERE c.username=:currentconsumer", Uploader.class);
        uploadersQuery.setParameter("currentconsumer", consumer.getUsername());

        List<Uploader> uploaders = uploadersQuery.getResultList();

        for (Uploader uploader : uploaders) {
            //a json for every uploader
            JSONObject jsonObjectUploader = new JSONObject();
            jsonObjectUploader.put("username", uploader.getUsername());
            if (uploader.getLogo() != null) {
                jsonObjectUploader.put("logo", Base64.getMimeEncoder().encodeToString(uploader.getLogo()));
            } else {
                jsonObjectUploader.put("logo", "");
            }

            //prelevare le informazioni relative per ogni file che verranno messe in un array
            JSONArray jsonArrayUploadedFiles = new JSONArray();
            TypedQuery<UploadedFile> uploadedFilesQuery = em.createQuery("SELECT uf "
                    + "FROM DownloadFile df INNER JOIN df.uploadedFile uf "
                    + "WHERE df.consumer=:currentconsumer AND uf.uploader=:currentuploader", UploadedFile.class);
            uploadedFilesQuery.setParameter("currentconsumer", consumer);
            uploadedFilesQuery.setParameter("currentuploader", uploader);

            List<UploadedFile> filesCaricati = uploadedFilesQuery.getResultList();

            for (UploadedFile uf : uploadedFilesQuery.getResultList()) {
                JSONObject jsonObjectUploadedFile = new JSONObject();
                jsonObjectUploadedFile.put("name", uf.getName());
                jsonObjectUploadedFile.put("uploaded", formatter.format(uf.getUploadDate()));
                jsonObjectUploadedFile.put("link", "/api/consumerarea/file/" + uf.getId() + "/" + uf.getName());
                if (uf.getHashtags() != null) {
                    jsonObjectUploadedFile.put("hashtags", uf.getHashtags());
                } else {
                    jsonObjectUploadedFile.put("hashtags", new JSONArray());
                }

                //let's fetch seen date
                TypedQuery<DownloadFile> downloadFileQuery = em.createQuery("SELECT df "
                        + "FROM DownloadFile df WHERE df.consumer=:currentconsumer AND df.uploadedFile=:currentuploadedfile", DownloadFile.class);
                downloadFileQuery.setParameter("currentconsumer", consumer);
                downloadFileQuery.setParameter("currentuploadedfile", uf);

                //a file might be seen or not
                if (downloadFileQuery.getSingleResult().getDownloaded() != null) {
                    jsonObjectUploadedFile.put("seen", formatter.format(downloadFileQuery.getSingleResult().getDownloaded()));
                } else {
                    jsonObjectUploadedFile.put("seen", "");
                }
                jsonArrayUploadedFiles.put(jsonObjectUploadedFile);
            }

            jsonObjectUploader.put("files", jsonArrayUploadedFiles);
            consumerNews.put(jsonObjectUploader);
        }

        em.close();
        emf.close();
        return consumerNews.toString();
    }

    @GET
    @Path("/consumer")
    @Produces(MediaType.APPLICATION_JSON)
    public RestConsumer consumer() {

        //get current consumer info for the private area page
        Consumer consumer = em.find(Consumer.class, (String) request.getSession().getAttribute("username"));
        //Consumer consumer = em.find(Consumer.class, "RSSMRA80B27F205P");
        RestConsumer restConsumer = new RestConsumer();
        restConsumer.setUsername(consumer.getUsername());
        restConsumer.setEmail(consumer.getEmail());
        restConsumer.setNameSurname(consumer.getNameSurname());
        restConsumer.setPassword(consumer.getPassword());

        //number of affiliate uploaders
        TypedQuery<Long> numUploadersQuery = em.createQuery("SELECT COUNT(cu) "
                + "FROM Consumer AS c INNER JOIN c.uploaders AS cu "
                + "WHERE c.username=:currentconsumer", Long.class);
        numUploadersQuery.setParameter("currentconsumer", consumer.getUsername());
        restConsumer.setTotUploaders(numUploadersQuery.getSingleResult());

        //number of files not seen yet
        TypedQuery<Long> numFilesUnseenQuery = em.createQuery("SELECT COUNT(dfu.id) "
                + "FROM DownloadFile AS df INNER JOIN df.uploadedFile dfu INNER JOIN df.consumer dfc "
                + "WHERE dfc.username=:currentconsumer AND df.downloaded=null", Long.class);
        numFilesUnseenQuery.setParameter("currentconsumer", consumer.getUsername());
        restConsumer.setUnseenFiles(numFilesUnseenQuery.getSingleResult());

        //number of files already seen
        TypedQuery<Long> numFilesSeenQuery = em.createQuery("SELECT COUNT(dfu.id) "
                + "FROM DownloadFile AS df INNER JOIN df.uploadedFile dfu INNER JOIN df.consumer dfc "
                + "WHERE dfc.username=:currentconsumer AND df.downloaded<>null", Long.class);
        numFilesSeenQuery.setParameter("currentconsumer", consumer.getUsername());
        restConsumer.setSeenFiles(numFilesSeenQuery.getSingleResult());

        restConsumer.setTotFiles(restConsumer.getSeenFiles() + restConsumer.getSeenFiles());

        em.close();
        emf.close();
        return restConsumer;
    }

    @POST
    @Path("/consumer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postConsumer(Consumer updates) {

        //change consumer info
        if (updates.getUsername() == null || updates.getEmail() == null
                || updates.getNameSurname() == null || updates.getPassword() == null) {
            return;
        }

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

    @GET
    @Path("/file/{id:[0-9]+}/{name:}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public byte[] getFile(@PathParam("id") long id, @PathParam("name") String name) throws IOException {

        try {
            UploadedFile uploadedFile = em.find(UploadedFile.class, id);

            TypedQuery<DownloadFile> dfQuery = em.createQuery("SELECT df FROM DownloadFile df "
                    + "WHERE df.uploadedFile=:uploadedFile AND df.consumer=:consumer", DownloadFile.class);
            dfQuery.setParameter("uploadedFile", uploadedFile);
            dfQuery.setParameter("consumer", em.find(Consumer.class, (String) request.getSession().getAttribute("username")));
            //dfQuery.setParameter("consumer", em.find(Consumer.class, "RSSMRA80B27F205P"));
            
            em.getTransaction().begin();
            DownloadFile df = dfQuery.getSingleResult();
            df.setDownloaded(new Date());
            df.setIpAddress(request.getRemoteAddr());
            em.persist(df);
            em.getTransaction().commit();

            em.close();
            emf.close();

            return uploadedFile.getContent();
            
        } catch (NoResultException e) {
            response.sendError(404, "file not found. Maybe the uploader deleted it");
            return null;
        }

    }

}

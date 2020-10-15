package units.honeycombstorage.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import units.honeycombstorage.entities.storage.Administrator;
import units.honeycombstorage.entities.storage.Uploader;

/**
 *
 * @author massi
 */
@Path("/administratorarea")
public class AdministratorArea {

    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("developmentPU");
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/generalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGeneralInfo() {

        //it will contain the number of administrator, uploaders, consumers and uploaded files
        JSONObject generalinfo = new JSONObject();

        TypedQuery<Long> administratorTotalQuery = em.createNamedQuery("Administrator.total", Long.class);
        TypedQuery<Long> uploaderTotalrQuery = em.createNamedQuery("Uploader.total", Long.class);
        TypedQuery<Long> consumerTotalQuery = em.createNamedQuery("Consumer.total", Long.class);
        TypedQuery<Long> uploadedFilesTotalQuery = em.createNamedQuery("UploadedFile.total", Long.class);

        generalinfo.put("totalAdministrators", administratorTotalQuery.getSingleResult());
        generalinfo.put("totalUploaders", uploaderTotalrQuery.getSingleResult());
        generalinfo.put("totalConsumers", consumerTotalQuery.getSingleResult());
        generalinfo.put("totalUploadedFiles", uploadedFilesTotalQuery.getSingleResult());

        em.close();
        emf.close();
        return generalinfo.toString();
    }

    @GET
    @Path("/uploaders")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUploaders() {

        JSONArray uploadersJSONArray = new JSONArray();
        TypedQuery<Uploader> uploadersQuery = em.createNamedQuery("Uploader.all", Uploader.class);
        for (Uploader uploader : uploadersQuery.getResultList()) {
            JSONObject uploaderJSON = new JSONObject();
            uploaderJSON.put("username", uploader.getUsername());
            uploaderJSON.put("email", uploader.getEmail());
            uploaderJSON.put("nameSurname", uploader.getNameSurname());
            if (uploader.getLogo() != null) {
                uploaderJSON.put("logo", Base64.getMimeEncoder().encodeToString(uploader.getLogo()));
            } else {
                uploaderJSON.put("logo", "");
            }
            uploaderJSON.put("password", uploader.getPassword());
            uploadersJSONArray.put(uploaderJSON);
        }
        em.close();
        emf.close();
        return uploadersJSONArray.toString();
    }

    @POST
    @Path("/uploader")
    public void addUploader(
            @FormParam("username") String username, @FormParam("nameSurname") String nameSurname,
            @FormParam("email") String email, @FormParam("password") String password) throws IOException {

        Uploader uploader = new Uploader();
        uploader.setUsername(username);
        uploader.setNameSurname(nameSurname);
        uploader.setEmail(email);
        uploader.setPassword(password);

        em.getTransaction().begin();
        if (em.find(Uploader.class, username) == null) {
            em.persist(uploader);
            response.sendRedirect(request.getHeader("referer"));
        } else {
            response.sendError(409, "the username is already taken");
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @POST
    @Path("/uploader")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyUploader(Uploader updates) {

        em.getTransaction().begin();
        Uploader uploader = em.find(Uploader.class, updates.getUsername());
        if (uploader != null) {
            uploader.setEmail(updates.getEmail());
            uploader.setNameSurname(updates.getNameSurname());
            uploader.setLogo(updates.getLogo());
            uploader.setPassword(updates.getPassword());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @DELETE
    @Path("/uploader/{username:}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUploader(@PathParam("username") String username) {

        em.getTransaction().begin();
        em.remove(em.find(Uploader.class, username));
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @GET
    @Path("/administrators")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdministrators() {

        JSONArray administratorsJSONArray = new JSONArray();
        TypedQuery<Administrator> administratorsQuery = em.createNamedQuery("Administrator.all", Administrator.class);
        for (Administrator administrator : administratorsQuery.getResultList()) {
            JSONObject administratorJSON = new JSONObject();
            administratorJSON.put("username", administrator.getUsername());
            administratorJSON.put("nameSurname", administrator.getNameSurname());
            administratorJSON.put("password", administrator.getPassword());
            administratorsJSONArray.put(administratorJSON);
        }
        em.close();
        emf.close();
        return administratorsJSONArray.toString();
    }

    @GET
    @Path("/administrator")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdministrator() {

        Administrator administrator = em.find(Administrator.class, (String) request.getSession().getAttribute("username"));
        //Administrator administrator = em.find(Administrator.class, "massimo.palmisano@gmail.com");
        JSONObject administratorJSON = new JSONObject();
        administratorJSON.put("username", administrator.getUsername());
        administratorJSON.put("nameSurname", administrator.getNameSurname());
        administratorJSON.put("password", administrator.getPassword());

        em.close();
        emf.close();
        return administratorJSON.toString();
    }

    @POST
    @Path("/administrator")
    public void addAdministrator(
            @FormParam("username") String username, @FormParam("nameSurname") String nameSurname,
            @FormParam("email") String email, @FormParam("password") String password) throws IOException {
        Administrator administrator = new Administrator();
        administrator.setUsername(username);
        administrator.setNameSurname(nameSurname);
        administrator.setEmail(email);
        administrator.setPassword(password);

        em.getTransaction().begin();
        if (em.find(Administrator.class, username) == null) {
            em.persist(administrator);
            response.sendRedirect(request.getHeader("referer"));
        } else {
            response.sendError(409, "the username is already taken");
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @POST
    @Path("/administrator")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putAdministrator(Administrator updates) {

        em.getTransaction().begin();
        Administrator administrator = em.find(Administrator.class, updates.getUsername());
        administrator.setEmail(updates.getUsername());
        administrator.setNameSurname(updates.getNameSurname());
        administrator.setPassword(updates.getPassword());
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @DELETE
    @Path("/administrator/{username}")
    public void deleteAdministrator(@PathParam("username") String username) {

        em.getTransaction().begin();
        em.remove(em.find(Administrator.class, username));
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @GET
    @Path("/uploadedfiles")
    @Produces(MediaType.APPLICATION_JSON)
    public String getParticularInfo() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        JSONArray particularInfoJSONArray = new JSONArray();
        TypedQuery<Uploader> uploadersQuery = em.createQuery("SELECT u FROM Uploader u", Uploader.class);

        for (Uploader uploader : uploadersQuery.getResultList()) {
            JSONObject uploaderJSONObject = new JSONObject();
            uploaderJSONObject.put("username", uploader.getUsername());

            JSONArray uploadedFilesJSONArray = new JSONArray();
            TypedQuery<Object[]> uploadedFilesQuery = em.createQuery("SELECT uf.id, uf.name, uf.uploadDate "
                    + "FROM UploadedFile uf WHERE uf.uploader=:currentuploader", Object[].class);
            uploadedFilesQuery.setParameter("currentuploader", uploader);

            for (Object[] uploadedFile : uploadedFilesQuery.getResultList()) {

                TypedQuery<Long> totConsumers = em.createQuery("SELECT COUNT(df.consumer) "
                        + "FROM DownloadFile df WHERE df.uploadedFile.id=:currentuploadedfile", Long.class);
                totConsumers.setParameter("currentuploadedfile", (Long)uploadedFile[0]);

                JSONObject uploadedFileJSONObject = new JSONObject();
                uploadedFileJSONObject.put("name", (String)uploadedFile[1]);
                uploadedFileJSONObject.put("upload", formatter.format((Date)uploadedFile[2]));
                uploadedFileJSONObject.put("totConsumers", totConsumers.getSingleResult());
                uploadedFilesJSONArray.put(uploadedFileJSONObject);
            }
            uploaderJSONObject.put("uploadedFiles", uploadedFilesJSONArray);
            particularInfoJSONArray.put(uploaderJSONObject);

        }

        em.close();
        emf.close();
        return particularInfoJSONArray.toString();
    }
}

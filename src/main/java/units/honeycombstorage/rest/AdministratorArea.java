package units.honeycombstorage.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import units.honeycombstorage.entites.Administrator;
import units.honeycombstorage.entites.UploadedFile;
import units.honeycombstorage.entites.Uploader;

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

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("productionPU");
    EntityManager em = emf.createEntityManager();

    @GET
    @Path("/generalinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGeneralInfo() {

        //it will contain the number of administrator, uploaders, consumers and uploaded files
        JSONObject generalinfo = new JSONObject();

        TypedQuery<Long> administratorNumberQuery = em.createQuery("SELECT COUNT(a.username) "
                + "FROM Administrator a", Long.class);
        TypedQuery<Long> uploaderNumberQuery = em.createQuery("SELECT COUNT(u.username) "
                + "FROM Uploader u", Long.class);
        TypedQuery<Long> consumerNumberQuery = em.createQuery("SELECT COUNT(c.username) "
                + "FROM Consumer c", Long.class);
        TypedQuery<Long> uploadedFilesNumberQuery = em.createQuery("SELECT COUNT(uf.id) "
                + "FROM UploadedFile uf", Long.class);

        generalinfo.put("totalAdministrators", administratorNumberQuery.getSingleResult());
        generalinfo.put("totalUploaders", uploaderNumberQuery.getSingleResult());
        generalinfo.put("totalConsumers", consumerNumberQuery.getSingleResult());
        generalinfo.put("totalUploadedFiles", uploadedFilesNumberQuery.getSingleResult());

        return generalinfo.toString();
    }

    @GET
    @Path("/uploaders")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUploaders() {

        JSONArray uploadersJSONArray = new JSONArray();
        TypedQuery<Uploader> uploadersQuery = em.createQuery("SELECT u FROM Uploader u", Uploader.class);
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
    }

    @PUT
    @Path("/uploader")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyUploader(Uploader uploader) {

        Uploader update = em.find(Uploader.class, uploader.getUsername());
        update.setEmail(uploader.getEmail());
        update.setNameSurname(uploader.getNameSurname());
        update.setLogo(uploader.getLogo());
        update.setPassword(uploader.getPassword());

        em.getTransaction().begin();
        em.persist(update);
        em.getTransaction().commit();
    }

    @DELETE
    @Path("/uploader/{username:}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUploader(@PathParam("username") String username) {

        em.getTransaction().begin();
        em.remove(em.find(Uploader.class, username));
        em.getTransaction().commit();
    }

    @GET
    @Path("/administrators")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdministrators() {

        JSONArray administratorsJSONArray = new JSONArray();
        TypedQuery<Administrator> administratorsQuery = em.createQuery("SELECT a "
                + "FROM Administrator a", Administrator.class);
        for (Administrator administrator : administratorsQuery.getResultList()) {
            JSONObject administratorJSON = new JSONObject();
            administratorJSON.put("username", administrator.getUsername());
            administratorJSON.put("nameSurname", administrator.getNameSurname());
            administratorJSON.put("password", administrator.getPassword());
            administratorsJSONArray.put(administratorJSON);
        }
        return administratorsJSONArray.toString();
    }

    @GET
    @Path("/administrator")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdministrator() {

        Administrator administrator = em.find(Administrator.class,(String)request.getSession().getAttribute("username"));
        JSONObject administratorJSON = new JSONObject();
        administratorJSON.put("username", administrator.getUsername());
        administratorJSON.put("nameSurname", administrator.getNameSurname());
        administratorJSON.put("password", administrator.getPassword());

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
    }

    @PUT
    @Path("/administrator")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyAdministrator(Administrator administrator) {

        em.getTransaction().begin();
        Administrator update = em.find(Administrator.class, administrator.getUsername());
        update.setNameSurname(administrator.getNameSurname());
        update.setPassword(administrator.getPassword());
        em.getTransaction().commit();
    }

    @DELETE
    @Path("/administrator/{username}")
    public void deleteAdministrator(@PathParam("username") String username) {

        em.getTransaction().begin();
        em.remove(em.find(Administrator.class, username));
        em.getTransaction().commit();
    }

    @GET
    @Path("/particularinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getParticularInfo() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        JSONArray particularInfoJSONArray = new JSONArray();
        TypedQuery<Uploader> uploadersQuery = em.createQuery("SELECT u FROM Uploader u", Uploader.class);

        for (Uploader uploader : uploadersQuery.getResultList()) {
            JSONObject uploaderJSONObject = new JSONObject();
            uploaderJSONObject.put("username", uploader.getUsername());

            JSONArray uploadedFilesJSONArray = new JSONArray();
            TypedQuery<UploadedFile> uploadedFilesQuery = em.createQuery("SELECT uf "
                    + "FROM UploadedFile uf WHERE uf.uploader=:currentuploader", UploadedFile.class);
            uploadedFilesQuery.setParameter("currentuploader", uploader);

            for (UploadedFile uploadedFile : uploadedFilesQuery.getResultList()) {

                TypedQuery<Long> totConsumers = em.createQuery("SELECT COUNT(df.consumer) "
                        + "FROM DownloadFile df WHERE df.uploadedFile=:currentuploadedfile", Long.class);
                totConsumers.setParameter("currentuploadedfile", uploadedFile);

                JSONObject uploadedFileJSONObject = new JSONObject();
                uploadedFileJSONObject.put("name", uploadedFile.getName());
                uploadedFileJSONObject.put("upload", formatter.format(uploadedFile.getUploadDate()));
                uploadedFileJSONObject.put("totConsumers", totConsumers.getSingleResult());
                uploadedFilesJSONArray.put(uploadedFileJSONObject);
            }
            uploaderJSONObject.put("uploadedFiles", uploadedFilesJSONArray);
            particularInfoJSONArray.put(uploaderJSONObject);

        }

        return particularInfoJSONArray.toString();
    }
}

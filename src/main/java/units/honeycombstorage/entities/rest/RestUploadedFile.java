package units.honeycombstorage.entities.rest;

/**
 * @author massi
 *
 * used in UploaderArea method @GET /files
 *
 */
public class RestUploadedFile {

    private long id;
    private String name;
    private String uploadDate;
    private long size;

    public RestUploadedFile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}

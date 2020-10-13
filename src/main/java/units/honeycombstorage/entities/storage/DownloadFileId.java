package units.honeycombstorage.entities.storage;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author massi
 */
@Embeddable
public class DownloadFileId implements Serializable {
    private String consumer_username;
    private long uploadedFileId;

    public DownloadFileId() {

    }

    public DownloadFileId(String consumer_username, long uploadedFileId) {
        super();
        this.consumer_username = consumer_username;
        this.uploadedFileId = uploadedFileId;
    }

    public String getConsumer_username() {
        return consumer_username;
    }

    public void setConsumer_username(String consumer_username) {
        this.consumer_username = consumer_username;
    }

    public long getUploadedFileId() {
        return uploadedFileId;
    }

    public void setUploadedFileId(long uploadedFileId) {
        this.uploadedFileId = uploadedFileId;
    }

}

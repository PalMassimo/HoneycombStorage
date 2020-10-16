package units.honeycombstorage.entities.storage;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author massi
 */
@Entity
public class DownloadFile implements Serializable {

    @EmbeddedId
    //serve a mapping the composite primary key: it consists of the ids
    //of the ids of the associated UploadedFile e Consumer entities
    //a tale scopo servono anche le annotation @MapsId
    private final DownloadFileId id = new DownloadFileId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("consumer_username")
    @Column(nullable = false)
    private Consumer consumer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("uploadedFileId")
    @Column(nullable = false)
    private UploadedFile uploadedFile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "downloaded", nullable = true)
    private Date downloaded;

    @Column(name = "ipAddress", nullable = true)
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Date getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Date downloaded) {
        this.downloaded = downloaded;
    }

}

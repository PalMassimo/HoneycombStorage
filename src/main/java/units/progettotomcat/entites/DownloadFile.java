package units.progettotomcat.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    
    @ManyToOne
    @MapsId("consumer_username")
    @NotNull
    private Consumer consumer;
    
    @ManyToOne
    @MapsId("uploadedFileId")
    @NotNull
    private UploadedFile uploadedFile;
    
    @Temporal(TemporalType.TIME)
    private Date downloaded;

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

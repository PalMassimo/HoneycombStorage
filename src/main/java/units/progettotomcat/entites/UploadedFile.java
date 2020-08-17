package units.progettotomcat.entites;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author massi
 */
@Entity
@Table(name = "UploadedFile")
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content"/*, columnDefinition="BLOB"*/)
    private byte[] content;

    @Column(name = "name")
    private String name;

    @Column(name = "uploadDate")
    @Temporal(value = TemporalType.TIME)
    private Date uploadDate;

    @Column(name = "seenDate")
    @Temporal(value = TemporalType.TIME)
    private Date seenDate;
    
    @Column(name="addressIP")
    private String addressIP;
    
    @Column(name = "hashtagList")
    private String[] hashtags;
    
    @Transient
    private String contentType;
    
    @ManyToOne
    @JoinColumn(name="uploader", nullable=false)
    private Uploader uploader;

    public String getAddressIP() {
        return addressIP;
    }

    public void setAddressIP(String addressIP) {
        this.addressIP = addressIP;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    
    
}
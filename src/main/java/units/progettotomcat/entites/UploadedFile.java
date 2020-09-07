package units.progettotomcat.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author massi
 */
@Entity(name = "UploadedFile")
@Table(name = "uploadedFile")
public class UploadedFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "name")
    private String name;

    @Column(name = "uploadDate")
    @Temporal(value = TemporalType.TIME)
    private Date uploadDate;

    @Column(name = "seenDate")
    @Temporal(value = TemporalType.TIME)
    private Date seenDate;

    @Column(name = "addressIP")
    private String addressIP;

    @Column(name = "hashtagList")
    private String[] hashtags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader", nullable = false)
    private Uploader uploader;

    @OneToMany(mappedBy = "uploadedFile", fetch = FetchType.LAZY)
    private Set<DownloadFile> downloadFiles;

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

package units.honeycombstorage.entities.storage;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@NamedQuery(name = "UploadedFile.total", query = "SELECT COUNT(id) FROM UploadedFile")
public class UploadedFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content", nullable = false)
    private byte[] content;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uploadDate", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date uploadDate;

    @Column(name = "hashtagList", nullable = false)
    private String[] hashtags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader", nullable = false)
    private Uploader uploader;

    @OneToMany(mappedBy = "uploadedFile", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Column(nullable = false)
    private Set<DownloadFile> downloadFiles;

    public Set<DownloadFile> getDownloadFiles() {
        return downloadFiles;
    }

    public void setDownloadFiles(Set<DownloadFile> downloadFiles) {
        this.downloadFiles = downloadFiles;
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

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

}

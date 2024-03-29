package units.honeycombstorage.entities.storage;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
// uploader entity
@Entity(name = "Uploader")
@Table(name = "uploader")
@NamedQueries({
    @NamedQuery(name = "Uploader.all", query = "SELECT u FROM Uploader u"),
    @NamedQuery(name = "Uploader.total", query = "SELECT COUNT(username) FROM Uploader"),
})
public class Uploader extends Utente implements Serializable {

    @Column(name = "logo", nullable = true)
    private byte[] logo;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<UploadedFile> uploadedFiles;

    @ManyToMany(mappedBy = "uploaders", fetch = FetchType.LAZY)
    private Set<Consumer> consumers;

    public Uploader() {
        super();
        logo = null;
    }

    public Set<Consumer> getConsumers() {
        return consumers;
    }

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Set<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(Set<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }
    
    public boolean equals(Uploader uploader1, Uploader uploader2){
       return (uploader1.getUsername()).equals(uploader2.getUsername());
    }

}

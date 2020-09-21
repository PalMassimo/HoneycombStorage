package units.progettotomcat.entites;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
@Entity(name = "Uploader")
@Table(name = "uploader")
public class Uploader extends Utente implements Serializable {

    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(mappedBy = "uploader", cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UploadedFile> uploadedFiles;

    @ManyToMany(mappedBy = "uploaders", fetch = FetchType.LAZY)
    private Set<Consumer> consumers;

    public Uploader() {
        super();
        logo = null;
    }

    @Override
    public String toString() {
        return username + email + nameSurname;
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

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

}

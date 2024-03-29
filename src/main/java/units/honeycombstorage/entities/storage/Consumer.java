package units.honeycombstorage.entities.storage;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
//consumer entity
@Entity(name = "Consumer")
@Table(name = "consumer")
@NamedQuery(name="Consumer.total", query="SELECT COUNT(username) FROM Consumer")
public class Consumer extends Utente implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Affiliation",
            joinColumns = @JoinColumn(name = "consumer_username"),
            inverseJoinColumns = @JoinColumn(name = "uploader_username"))
    Set<Uploader> uploaders = new HashSet<Uploader>();
    
    @OneToMany(mappedBy = "consumer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<DownloadFile> downloadFiles;

    public Consumer() {
        super();
    }

    public Set<Uploader> getUploaders() {
        return uploaders;
    }

    public void setUploaders(Set<Uploader> uploaders) {
        this.uploaders = null;
    }

    public void addUploader(Uploader uploader) {
        this.uploaders.add(uploader);
    }
    
    public void removeUploader(Uploader uploader){
        this.uploaders.remove(uploader);
    }

    public Set<DownloadFile> getDownloadFiles() {
        return downloadFiles;
    }

    public void setDownloadFiles(Set<DownloadFile> downloadFiles) {
        this.downloadFiles = null;
    }

}

package units.progettotomcat.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
@Entity(name = "Consumer")
@Table(name = "consumer")
public class Consumer extends Utente implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Affiliation",
            joinColumns = @JoinColumn(name = "consumer_username"),
            inverseJoinColumns = @JoinColumn(name = "uploader_username"))
    Set<Uploader> uploaders = new HashSet<Uploader>(0);

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private Set<DownloadFile> downloadFiles;

    public Consumer() {
        super();
    }

    public Set<Uploader> getUploaders() {
        return uploaders;
    }
    
    public void setUploaders(Set<Uploader> uploaders){
        this.uploaders=null;
    }

    public void addUploader(Uploader uploader) {
        this.uploaders.add(uploader);
    }

    public Set<DownloadFile> getDownloadFiles() {
        return downloadFiles;
    }

    public void setDownloadFiles(Set<DownloadFile> downloadFiles) {
        this.downloadFiles=null;
    }
    
    

}

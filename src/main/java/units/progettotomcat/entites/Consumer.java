package units.progettotomcat.entites;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
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

    @ManyToMany
    @JoinTable(
            name = "Affiliation",
            joinColumns = @JoinColumn(name = "consumer_username"),
            inverseJoinColumns = @JoinColumn(name = "uploader_username"))
    Set<Uploader> uploaders;

    @OneToMany(mappedBy="consumer")
    private Set<DownloadFile> downloadFiles;

    public Consumer() {
        super();
    }

    public Set<Uploader> getUploaders() {
        return uploaders;
    }

    public void addUploader(Uploader uploader) {
        uploaders.add(uploader);
    }
}

package units.progettotomcat.entites;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
@Entity
@Table(name = "consumer")
public class Consumer extends Utente {

    @ManyToMany
    @JoinTable(name="consumers_uploaders",
            joinColumns=@JoinColumn(name="consumer_id"),
            inverseJoinColumns =@JoinColumn(name="uploader_id"))
    List<Uploader> uploaders = new ArrayList<>();

    public Consumer() {
        super();
    }

    public List<Uploader> getUploaders() {
        return uploaders;
    }

    public void addUploader(Uploader uploader) {
        uploaders.add(uploader);
    }

    public void deleteUploader(Uploader uploader) {
        //IMPLEMENTA
    }

}

package units.progettotomcat.entites;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
@Entity
@Table(name = "uploader")
public class Uploader extends Utente {

    @Column(name = "logo")
    private byte[] logo;

    //The mappedBy property is what we use to tell Hibernate which variable ...
    //...we are using to represent the parent class in our child class.
    @OneToMany(mappedBy="uploader")
    private List<UploadedFile> uploadedFiles;

    @ManyToMany(mappedBy="uploaders")
    private List<Consumer> consumers = new ArrayList<>();

    public Uploader() {
        super();
        logo = null;
    }

    @Override
    public String toString() {
        return username + email + nomecognome;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }
    
    public void deleteConsumer(Consumer consumer){
        //implementa
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

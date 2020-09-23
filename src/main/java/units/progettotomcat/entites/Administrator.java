package units.progettotomcat.entites;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
@Entity
@Table(name = "administrator")
public class Administrator extends Utente {

    public Administrator() {
        super();
    }

    public Administrator(String username, String email, String nomecognome, String password) {
        super(username, email, nomecognome, password);
    }
}

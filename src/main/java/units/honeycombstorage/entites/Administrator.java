package units.honeycombstorage.entites;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author massi
 */
// Administrator entity
@Entity
@Table(name = "administrator")
@NamedQueries({
    @NamedQuery(name = "Administrator.total", query = "SELECT COUNT(username) FROM Administrator"),
    @NamedQuery(name = "Administrator.all", query = "SELECT a FROM Administrator a")
})

public class Administrator extends Utente {

    public Administrator() {
        super();
    }

    public Administrator(String username, String email, String nomecognome, String password) {
        super(username, email, nomecognome, password);
    }
}

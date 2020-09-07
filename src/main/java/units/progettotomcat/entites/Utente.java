package units.progettotomcat.entites;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 *
 * @author massi
 */
@MappedSuperclass
public class Utente implements Serializable {

    @Id
    protected String username;
    
    @NotNull
    protected String email;
    
    @NotNull
    protected String nomecognome;
    
    @NotNull
    protected String password;

    public Utente() {
        username = null;
        email = null;
        nomecognome = null;
        password = null;
    }

    public Utente(String username, String email, String nomecognome, String password) {
        this.username = username;
        this.email = email;
        this.nomecognome = nomecognome;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCognome() {
        return nomecognome;
    }

    public void setNomeCognome(String nomecognome) {
        this.nomecognome = nomecognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

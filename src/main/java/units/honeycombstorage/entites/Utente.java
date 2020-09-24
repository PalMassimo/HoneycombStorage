package units.honeycombstorage.entites;

import java.io.Serializable;
import javax.persistence.Column;
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

    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "namesurname", nullable = false)
    protected String nameSurname;

    @Column(name = "password", nullable = false)
    protected String password;

    public Utente() {
        username = null;
        email = null;
        nameSurname = null;
        password = null;
    }

    public Utente(String username, String email, String nomecognome, String password) {
        this.username = username;
        this.email = email;
        this.nameSurname = nomecognome;
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

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nomecognome) {
        this.nameSurname = nomecognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

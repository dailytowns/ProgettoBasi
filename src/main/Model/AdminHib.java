package main.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Classe addetta alla modellizzazione di un amministratore
 * @author Federico Amici
 */
@Entity
@Table(name="amministratore")
public class AdminHib implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userid")
    private String userid;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;

    public AdminHib () {

    }

    public AdminHib(String userid, String password, String name, String surname, String email) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surnname) {
        this.surname = surnname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

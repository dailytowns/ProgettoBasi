package Model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by feder on 06/12/2016.
 */
@Entity
@Table(name="registereduser")
public class UserHib implements Serializable {

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

    public UserHib () {

    }

    public UserHib(String userid, String password, String name, String surname, String email) {
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


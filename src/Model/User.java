package Model;

import Helper.PsqlDBHelper;
import sun.security.util.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by feder on 27/09/2016.
 */
public class User {

    private String name;
    private String surname;
    private String email;
    private String userId;
    private String password;

    public User(String name, String surname, String email, String userId, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void searchGalaxyForName() {

    }

    public void searchGalaxyForRadius() {

    }

}

package main.Model;

/**
 * Classe model dell'utente registrato
 * @author Federico Amici
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

}

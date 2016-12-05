package Model;

import Helper.PsqlDBHelper;
import sun.security.util.Password;

import java.sql.*;
import java.util.Properties;

/**
 * Created by feder on 27/09/2016.
 */
public class Admin extends User {

    public Admin(String name, String surname, String email, String userId, String password) {
        super(name, surname, email, userId, password);
    }

    public void import_file_data(){

        //update_values();
    }

    public void import_file_reference(){

    }

    public void register_user(User user) {
        //PsqlDBHelper psqlDBHelper = new PsqlDBHelper("postgres", "portento123");
        //psqlDBHelper.registerUser(user);

    }

    public static void main(String[] args) {
        Admin a = new Admin("mario", "rossi", "mail", "user", "pass");
        User user = new User("federico", "amici", "mail", "username", "password");
        a.register_user(user);
    }
}

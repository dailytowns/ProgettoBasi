package Model;

import sun.security.util.Password;

/**
 * Created by feder on 27/09/2016.
 */
public class Admin extends User {

    public Admin(String name, String surname, String email, String userId, Password password) {
        super(name, surname, email, userId, password);
    }

    public void import_file_data(){

        //update_values();
    }

    public void import_file_reference(){



    }

    public void register_user() {

    }

}

package main.Control;

import main.Helper.UserDAO;
import main.Model.User;
import main.View.UserCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Classe control della view relativa all'elenco degli
 * utenti registrati nel database
 * @author Federico Amici
 */
public class ListUsersControl {

    @FXML
    private ListView listUsers;

    /**
     * Il metodo inizializza tutti i nodi della view cui deve essere
     * associato un listener. Vengono inoltre recuperati i dati utili
     * alla composizione della lista
     */
    @FXML
    public void initialize() {
        UserDAO userDAO = new UserDAO();
        ObservableList<User> list = userDAO.retrieveAllUser();
        listUsers.setItems(list);
        listUsers.setCellFactory(userCell -> new UserCell());
    }
}

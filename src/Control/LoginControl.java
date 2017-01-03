package Control;

import Helper.UserDAO;
import View.AdminView;
import View.UserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * @author Federico Amici
 * Classe Control della StartView. Si occupa di analizzare i dati
 * inseriti dall'utente che vuole autenticarsi nel sistema.
 */
public class LoginControl {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pswUser;
    @FXML
    private Button btnOk;


    /**
     * Il metodo si occupa di associare un listener al bottone di OK e un opportuno
     * metodo da eseguire alla ricezione dell'evento
     */
    @FXML
    public void initialize() {
        btnOk.setDefaultButton(true);
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (txtUser.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attenzione!");
                    alert.setHeaderText(null);
                    alert.setContentText("Inserisci il tuo nome utente");
                    alert.showAndWait();
                } else {    /*Cerca nel database il matching tra username e password*/
                    UserDAO userDAO = new UserDAO();
                    boolean check = userDAO.checkUser(txtUser.getText(), pswUser.getText());
                    if (check) {
                        new UserView();
                        //hide this current window (if this is whant you want
                        btnOk.getScene().getWindow().hide();
                    } else {
                        check = userDAO.checkAdmin(txtUser.getText(), pswUser.getText());
                        if (check) {
                            new AdminView();
                            btnOk.getScene().getWindow().hide();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Attenzione!");
                            alert.setHeaderText(null);
                            alert.setContentText("Lo username o la password non sono corretti");
                            alert.showAndWait();
                        }
                    }
                }
            }
        });
    }
}
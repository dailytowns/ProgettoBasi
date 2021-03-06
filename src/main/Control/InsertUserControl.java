package main.Control;

import main.Helper.AdminDAO;
import main.Helper.UserDAO;
import main.Model.AdminHib;
import main.Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;

/**
 * Classe control della view di inserimento di un nuovo
 * utente nel sistema
 * @author Federico Amici
 */
public class InsertUserControl {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnConferma;
    @FXML
    private Button btnReset;
    @FXML
    private CheckBox ckbxAmm;

    /**
     * Il metodo inizializza tutti i nodi della view cui deve essere
     * associato un listener.
     */
    @FXML
    public void initialize() {
        btnConferma.setDefaultButton(true);

        btnConferma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!checkUserAndPassword(txtUsername.getText(), txtPassword.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attenzione!");
                    alert.setHeaderText(null);
                    alert.setContentText("Il nome utente o la password non rispettano i vincoli predefiniti");
                    alert.showAndWait();
                } else {
                    /*Questo if implementa la RV9*/
                    if(!(txtUsername.getText().equals(txtPassword.getText()))) {
                        if (ckbxAmm.selectedProperty().getValue() == false) {
                            User user = new User(txtNome.getText(), txtCognome.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText());
                            UserDAO userDAO = new UserDAO();
                            userDAO.insertUser(user, "registereduser");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Attenzione!");
                            alert.setHeaderText(null);
                            alert.setContentText("Utente registrato correttamente");
                            alert.showAndWait();
                            btnConferma.getScene().getWindow().hide();
                        } else {
                            AdminHib user = new AdminHib(txtUsername.getText(), txtPassword.getText(), txtNome.getText(), txtCognome.getText(), txtEmail.getText());
                            AdminDAO adminDAO = new AdminDAO();
                            adminDAO.insertRecordsHibernate(user);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Attenzione!");
                            alert.setHeaderText(null);
                            alert.setContentText("Amministratore registrato correttamente");
                            alert.showAndWait();
                            btnConferma.getScene().getWindow().hide();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Attenzione!");
                        alert.setHeaderText(null);
                        alert.setContentText("La password non pu� coincidere con lo username");
                        alert.showAndWait();
                    }
                }
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtPassword.setText("");
                txtUsername.setText("");
                txtCognome.setText("");
                txtNome.setText("");
                txtEmail.setText("");
            }
        });
    }

    /**
     * Il metodo assicura che vengano rispettate le RV1 e RV2
     * Reso statico sia per essere accessibile dalla pagina web sia per testing
     * @param user UserID immesso
     * @param password Password immessa
     * @return True o False rispettivamente se le regole di vincolo sono state o meno
     * rispettate
     */
    public static boolean checkUserAndPassword(String user, String password) {
        if(user.length() < 6 || password.length() < 6)
            return false;
        int numbers = 0, i=0;
        while(i < password.length()) {
            if (Character.isDigit(password.charAt(i)))
                numbers++;
            i++;
        }
        return numbers >= 2;
    }
}

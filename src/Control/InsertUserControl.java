package Control;

import Helper.PsqlDBHelper;
import Helper.UserDAO;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;

/**
 * Created by feder on 05/12/2016.
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
                            btnConferma.getScene().getWindow().hide();
                        } else {
                            User user = new User(txtNome.getText(), txtCognome.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText());
                            UserDAO userDAO = new UserDAO();
                            userDAO.insertUser(user, "amministratore");
                            btnConferma.getScene().getWindow().hide();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Attenzione!");
                        alert.setHeaderText(null);
                        alert.setContentText("La password non può coincidere con lo username");
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

    /*Il metodo assicura che venga rispettata la RV#*/
    private boolean checkUserAndPassword(String user, String password) {
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

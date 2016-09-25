package Control;

/**
 * Created by feder on 25/09/2016.
 */

import View.AdminView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginControl {
    @FXML private TextField txtUser;
    @FXML private PasswordField pswUser;
    @FXML private Button btnOk;

    @FXML public void initialize() {
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
             public void handle(ActionEvent event) {
                 if (txtUser.getText().equals("")){
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Attenzione!");
                     alert.setHeaderText(null);
                     alert.setContentText("Inserisci il tuo nome utente");
                     alert.showAndWait();
                 } else {
                     Parent root;
                     try {
                         root = FXMLLoader.load(getClass().getClassLoader().getResource("View/AdminView.fxml"));
                         Stage stage = new Stage();
                         stage.setTitle("My New Stage Title");
                         stage.setScene(new Scene(root, 450, 450));
                         stage.show();

                         //hide this current window (if this is whant you want
                         btnOk.getScene().getWindow().hide();

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }
        });
    }
}
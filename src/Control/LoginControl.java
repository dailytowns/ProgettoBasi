package Control;

/**
 * Created by feder on 25/09/2016.
 */

import Helper.PsqlDBHelper;
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
import javassist.tools.Dump;
import org.postgresql.copy.CopyManager;

import javax.swing.*;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class LoginControl {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pswUser;
    @FXML
    private Button btnOk;


    @FXML
    public void initialize() {
        btnOk.setDefaultButton(true);
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                LoginControl.this.handle(event);
            }
        });

        dumpDB();
    }

    private void dumpDB() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        if(!psqlDBHelper.checkGalaxyTable())
            psqlDBHelper.createTableGalassia();
    }

    private void handle(ActionEvent e) {

        /*Nessun input presente*/
        if (txtUser.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attenzione!");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci il tuo nome utente");
            alert.showAndWait();
        } else {    /*Cerca nel database il matching tra username e password*/
            PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
            boolean check = psqlDBHelper.checkUser(txtUser.getText(), pswUser.getText());

            if (check) {

                AdminView adminView = new AdminView();

                //hide this current window (if this is whant you want
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
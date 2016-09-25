package View;/**
 * Created by feder on 25/09/2016.
 */

import Control.LoginControl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartView extends Application {

    @FXML private Text txtUser;
    @FXML private Button btnOk;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Galaxian");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

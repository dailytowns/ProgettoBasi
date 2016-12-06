package View;/**
 * Created by Federico Amici on 25/09/2016.
 *
 * This is the entry class of the standalone application. Its assignment
 * is to load the .fxml files and receive the input that has to be parsed.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartView extends Application {

    @FXML private Label lblGalaxian;
    @FXML private Text txtUser;
    @FXML private PasswordField pswUser;
    @FXML private Button btnOk;
    @FXML private BorderPane basePane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartViewCSS2.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

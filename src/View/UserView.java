package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Federico Amici
 * Classe adibita al caricamento dell'interfaccia grafica vista da un utente registrato.
 */
public class UserView {

    public UserView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/UserView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Utente registrato");
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}



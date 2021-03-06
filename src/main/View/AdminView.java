package main.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe adibita al caricamento dell'interfaccia grafica per un utente amministratore.
 * @author Federico Amici
 */
public class AdminView {

    @FXML private Label label;

    public AdminView () {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main/resources/fxml/AdminView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Amministrazione");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();

    }

}

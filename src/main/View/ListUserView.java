package main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * main.View che elenca gli utenti presenti nel sistema
 * @author Federico Amici
 */
public class ListUserView {

    public ListUserView () {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/ListUsers.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lista utenti");
        primaryStage.show();
    }

}

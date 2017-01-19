package main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchGalaxyForRadiusView {

    public SearchGalaxyForRadiusView () {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/SearchGalaxyForRadius.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 660, 660);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ricerca galassie per raggio");
        primaryStage.show();
    }

}

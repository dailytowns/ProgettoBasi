package main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SearchRatioFluxView {

    public SearchRatioFluxView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/SearchRatioFluxView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rapporti fra le righe");
        primaryStage.show();
    }

}

package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by feder on 06/01/2017.
 */
public class StatiRatioFluxView {

    public StatiRatioFluxView() {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/StatisticsValuesRatioFlux.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Amministrazione");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

}

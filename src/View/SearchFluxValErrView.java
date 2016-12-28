package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by feder on 27/12/2016.
 */
public class SearchFluxValErrView {

    public SearchFluxValErrView () {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/SearchFluxValErr.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

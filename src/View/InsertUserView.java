package View;

import Control.InsertUserControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by feder on 05/12/2016.
 */
public class InsertUserView {

    /*Il file fxml deve associare la root dell'albero ad un controller, pena il return di null*/
    public InsertUserView() {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/InsertUserView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //InsertUserControl insertUserControl = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setTitle("Amministrazione");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }

}

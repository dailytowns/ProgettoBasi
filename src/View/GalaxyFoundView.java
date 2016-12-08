package View;

import Control.GalaxyFoundControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by feder on 07/12/2016.
 */
public class GalaxyFoundView {

    public GalaxyFoundView (String name, String altName, double redshift) {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/fxml/GalaxyFound.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        GalaxyFoundControl controller = fxmlLoader.getController();
        controller.setGalaxyName(name);
        controller.setGalaxyAltName(altName);
        controller.setRedshift(redshift);
        Scene newScene = new Scene(root, 640, 480);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

}

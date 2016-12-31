package View;

import Control.GalaxyFoundControl;
import Model.Galaxy;
import Model.GalaxyData;
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

    public GalaxyFoundView (GalaxyData galaxyData) {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/fxml/GalaxyFound.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        GalaxyFoundControl controller = fxmlLoader.getController();
        controller.setLblGalaxyName(galaxyData.getNomeGalassia());
        controller.setLblRedshift(galaxyData.getRedshift());
        controller.setLblLuminosita(galaxyData.getCaratteristicheFisiche().getLuminosita().getValore());
        controller.setLblMetallicita(galaxyData.getCaratteristicheFisiche().getMetallicita().getValore());
        controller.setLblRifLuminosita(galaxyData.getCaratteristicheFisiche().getLuminosita().getRiferimento());
        controller.setLblRifMetallicita(galaxyData.getCaratteristicheFisiche().getMetallicita().getRiferimento());
        controller.setLblARh(galaxyData.getCoordinateAngolari().getRightAscension().getHour());
        controller.setLblARm(galaxyData.getCoordinateAngolari().getRightAscension().getMinute());
        controller.setLblARs(galaxyData.getCoordinateAngolari().getRightAscension().getSeconds());
        controller.setLblDecDeg(galaxyData.getCoordinateAngolari().getDeclination().getDegrees());
        controller.setLblDecMin(galaxyData.getCoordinateAngolari().getDeclination().getMinute());
        controller.setLblDecSec(galaxyData.getCoordinateAngolari().getDeclination().getSeconds());
        controller.setLblDecSign(galaxyData.getCoordinateAngolari().getDeclination().getSign());

        Scene newScene = new Scene(root, 640, 480);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

}

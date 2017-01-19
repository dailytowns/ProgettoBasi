package main.View;

import main.Control.GalaxySelectedControl;
import main.Model.Galaxy;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe costituisce la main.View con i dati della galassia recuperati
 * dalla base di dati
 * @author Federico Amici
 */
public class GalaxySelectedView {

    public GalaxySelectedView(Galaxy galaxy) {
        Parent root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/GalaxyFound.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        GalaxySelectedControl controller = fxmlLoader.getController();
        controller.setLblGalaxyName(galaxy.getName());
        controller.setLblRedshift(galaxy.getRedshift());

        if(galaxy.getCaratteristicheFisiche().getLuminosita() != null) {
            if(galaxy.getCaratteristicheFisiche().getLuminosita().getValore() != null)
                controller.setLblLuminosita(galaxy.getCaratteristicheFisiche().getLuminosita().getValore());
            if(galaxy.getCaratteristicheFisiche().getLuminosita().getRiferimento() != null)
                controller.setLblRifLuminosita(galaxy.getCaratteristicheFisiche().getLuminosita().getRiferimento());
        }

        if(galaxy.getCaratteristicheFisiche().getMetallicita() != null) {
            if(galaxy.getCaratteristicheFisiche().getMetallicita().getValore() != null)
                controller.setLblMetallicita(galaxy.getCaratteristicheFisiche().getMetallicita().getValore());
            if (galaxy.getCaratteristicheFisiche().getMetallicita().getRiferimento() != null)
                controller.setLblRifMetallicita(galaxy.getCaratteristicheFisiche().getMetallicita().getRiferimento());
        }
        controller.setLblARh(galaxy.getCoordinateAngolari().getRightAscension().getHour());
        controller.setLblARm(galaxy.getCoordinateAngolari().getRightAscension().getMinute());
        controller.setLblARs(galaxy.getCoordinateAngolari().getRightAscension().getSeconds());
        controller.setLblDecDeg(galaxy.getCoordinateAngolari().getDeclination().getDegrees());
        controller.setLblDecMin(galaxy.getCoordinateAngolari().getDeclination().getMinute());
        controller.setLblDecSec(galaxy.getCoordinateAngolari().getDeclination().getSeconds());
        controller.setLblDecSign(galaxy.getCoordinateAngolari().getDeclination().getSign());

        Scene newScene = new Scene(root, 640, 480);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.isAlwaysOnTop();
        newStage.show();
    }

}

package View;

import Model.Galaxy;
import Model.GalaxyData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by feder on 16/01/2017.
 */
public class GalaxyExtendedCell extends ListCell<Galaxy> {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblNomeAlt;
    @FXML
    private Label lblRedshift;
    @FXML
    private Label lblRefLum;
    @FXML
    private Label lblMetallicita;
    @FXML
    private Label lblDecSign;
    @FXML
    private Label lblDecDeg;
    @FXML
    private Label lblDecMin;
    @FXML
    private Label lblDecSec;
    @FXML
    private Label lblArh;
    @FXML
    private Label lblArm;
    @FXML
    private Label lblArs;
    @FXML
    private AnchorPane basePane;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Galaxy galaxy, boolean empty) {
        super.updateItem(galaxy, empty);

        if(empty || galaxy == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/GalaxyExtendedCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lblNome.setText(String.valueOf(galaxy.getName()));
            lblNomeAlt.setText(galaxy.getAltName());
            lblRedshift.setText(String.valueOf(galaxy.getRedshift()));
            lblArh.setText(String.valueOf(galaxy.getCoordinateAngolari().getRightAscension().getHour()));
            lblArm.setText(String.valueOf(galaxy.getCoordinateAngolari().getRightAscension().getMinute()));
            lblArs.setText(String.valueOf(galaxy.getCoordinateAngolari().getRightAscension().getSeconds()));
            lblDecDeg.setText(String.valueOf(galaxy.getCoordinateAngolari().getDeclination().getDegrees()));
            lblDecMin.setText(String.valueOf(galaxy.getCoordinateAngolari().getDeclination().getMinute()));
            lblDecSec.setText(String.valueOf(galaxy.getCoordinateAngolari().getDeclination().getSeconds()));
            lblDecSign.setText(galaxy.getCoordinateAngolari().getDeclination().getSign());
            if(galaxy.getCaratteristicheFisiche()!=null) {
                if (galaxy.getCaratteristicheFisiche().getMetallicita() != null)
                    lblMetallicita.setText(String.valueOf(galaxy.getCaratteristicheFisiche().getMetallicita().getValore()));
                if (galaxy.getCaratteristicheFisiche().getLuminosita().getRiferimento() != null)
                    lblRefLum.setText(String.valueOf(galaxy.getCaratteristicheFisiche().getLuminosita().getRiferimento()));
                //lblNumber.setText("");
            }
            setGraphic(basePane);
        }

    }


}

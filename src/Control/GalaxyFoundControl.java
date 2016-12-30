package Control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by feder on 08/12/2016.
 */
public class GalaxyFoundControl {

    @FXML
    private Label lblNomeGalassia;
    @FXML
    private Label lblNomeAltGalassia;
    @FXML
    private Label lblRedshiftGalassia;

    public void setGalaxyName(String name) {
        this.lblNomeGalassia.setText(name);
    }

    public void setGalaxyAltName(String altName) {
        this.lblNomeAltGalassia.setText(altName);
    }

    public void setRedshift(double redshift) {
        this.lblRedshiftGalassia.setText(String.valueOf(redshift));
    }
}

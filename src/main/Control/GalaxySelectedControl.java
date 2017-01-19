package main.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Classe control della view che mostra i dati sulla galassia
 * selezionata dall'utente
 * @author Federico Amici
 */
public class GalaxySelectedControl {

    @FXML
    private Label lblNomeGalassia;
    @FXML
    private Label lblRedshiftGalassia;
    @FXML
    private Label lblMetallicita;
    @FXML
    private Label lblLuminosita;
    @FXML
    private Label lblRifMetallicita;
    @FXML
    private Label lblRifLuminosita;
    @FXML
    private Label lblDecSign;
    @FXML
    private Label lblDecDeg;
    @FXML
    private Label lblDecMin;
    @FXML
    private Label lblDecSec;
    @FXML
    private Label lblARh;
    @FXML
    private Label lblARm;
    @FXML
    private Label lblARs;


    public void setLblGalaxyName(String name) {
        this.lblNomeGalassia.setText(name);
    }

    public void setLblRedshift(double redshift) {
        this.lblRedshiftGalassia.setText(String.valueOf(redshift));
    }

    public void setLblMetallicita(double metallicita) {
        this.lblMetallicita.setText(String.valueOf(metallicita));
    }

    public void setLblLuminosita(double luminosita) {
        this.lblLuminosita.setText(String.valueOf(luminosita));
    }

    public void setLblRifMetallicita(int rifMetallicita) {
        this.lblRifMetallicita.setText(String.valueOf(rifMetallicita));
    }

    public void setLblRifLuminosita(int rifLuminosita) {
        this.lblRifLuminosita.setText(String.valueOf(rifLuminosita));
    }

    public void setLblDecSign(String sign) {
        this.lblDecSign.setText(sign);
    }

    public void setLblDecDeg(Integer deg) {
        this.lblDecDeg.setText(String.valueOf(deg));
    }

    public void setLblDecMin(Integer min) {
        this.lblDecMin.setText(String.valueOf(min));
    }

    public void setLblDecSec(Double sec) {
        this.lblDecSec.setText(String.valueOf(sec));
    }

    public void setLblARh(Integer hour) {
        this.lblARh.setText(String.valueOf(hour));
    }

    public void setLblARm(Integer min) {
        this.lblARm.setText(String.valueOf(min));
    }

    public void setLblARs(Double sec) {
        this.lblARs.setText(String.valueOf(sec));
    }
}

package Control;

import Helper.PsqlDBHelper;
import Model.Flux;
import View.FluxCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by feder on 29/12/2016.
 */
public class SearchRatioFluxViewControl {

    @FXML
    private Button btnOK;
    @FXML
    private TextField txtGalassia;
    @FXML
    private TextField txtPrimaRiga;
    @FXML
    private TextField txtSecondaRiga;
    @FXML
    private Label lblRapporto;
    @FXML
    private Label lblLimit;
    @FXML
    private ComboBox comboFluxType1;
    @FXML
    private ComboBox comboFluxType2;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String galassia = txtGalassia.getText();
                String[] primaRiga = txtPrimaRiga.getText().split(";");
                String[] secondaRiga = txtSecondaRiga.getText().split(";");

                String tipoFlusso1 = parseCombo(comboFluxType1);
                String tipoFlusso2 = parseCombo(comboFluxType2);

                Flux val1 = retrieveValueDB(galassia, primaRiga[0], tipoFlusso1);
                Flux val2 = retrieveValueDB(galassia, secondaRiga[0], tipoFlusso2);

                if(val1!=null && val2!=null) {
                    if (val1.getUpperLimit().contains("<") && !val2.getUpperLimit().contains("<"))
                        lblLimit.setText("UpperLimit");
                    else if (!val1.getUpperLimit().contains("<") && val2.getUpperLimit().contains("<"))
                        lblLimit.setText("LowerLimit");
                    else if (val1.getUpperLimit().contains("<") && val2.getUpperLimit().contains("<"))
                        lblLimit.setText("Entrambi upperlimit (?)");

                    Double res = val1.getValore() / val2.getValore();
                    lblRapporto.setText(String.valueOf(res));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Info");
                    alert.setHeaderText(null);
                    alert.setContentText("Non sono state trovate corrispondenze");
                    alert.showAndWait();
                }
            }
        });
    }

    private Flux retrieveValueDB(String galaxy, String atom, String table) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        Flux result = psqlDBHelper.retrieveValFluxDB(galaxy, atom, table);
        return result;
    }

    private String parseCombo(ComboBox combo) {

        if(combo.getSelectionModel().getSelectedIndex() == 0) {
            return "flusso";
        }
        else if(combo.getSelectionModel().getSelectedIndex() == 1) {
            return "flussocontinuo";
        }
        else if(combo.getSelectionModel().getSelectedIndex() == 2) {
            return "flussosp";
        }
        else
            return null;
    }
}

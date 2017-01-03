package Control;

import Helper.FluxDAO;
import Helper.PsqlDBHelper;
import Model.Flux;
import View.FluxCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @author Federico Amici
 * Classe Control che gestisce la View SearchRatioFluxViewControl,
 * che si occupa di presentare i risultati del rapporto tra due flussi
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
    private ComboBox comboRiga1;
    @FXML
    private ComboBox comboRiga2;

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
                    else
                        lblLimit.setText("Nessuno dei due è un upperLimit");

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
        comboFluxType1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(comboFluxType1.getSelectionModel().getSelectedIndex() == 0) {
                    if(comboRiga1.getItems().size() != 0)
                        comboRiga1.getItems().removeAll(comboRiga1.getItems());
                    comboRiga1.getItems().addAll(
                            "OIII52",
                            "NIII57",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158");
                }
                else if(comboFluxType1.getSelectionModel().getSelectedIndex() == 1) {
                    if(comboRiga1.getItems().size() != 0)
                        comboRiga1.getItems().removeAll(comboRiga1.getItems());
                    comboRiga1.getItems().addAll(
                            "OIII52",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158"
                    );
                }
                else if(comboFluxType1.getSelectionModel().getSelectedIndex() == 2) {
                    if(comboRiga1.getItems().size() != 0)
                        comboRiga1.getItems().removeAll(comboRiga1.getItems());
                    comboRiga1.getItems().addAll(
                            "SIV",
                            "NeII",
                            "NeV14.3",
                            "NeIII",
                            "SIII18.7",
                            "Nev24.3",
                            "OIV",
                            "SIII33.5",
                            "SiII"
                    );
                }
            }
        });
        comboFluxType2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(comboFluxType2.getSelectionModel().getSelectedIndex() == 0) {
                    if(comboRiga2.getItems().size() != 0)
                        comboRiga2.getItems().removeAll(comboRiga2.getItems());
                    comboRiga2.getItems().addAll(
                            "OIII52",
                            "NIII57",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158");
                }
                else if(comboFluxType2.getSelectionModel().getSelectedIndex() == 1) {
                    if(comboRiga2.getItems().size() != 0)
                        comboRiga2.getItems().removeAll(comboRiga2.getItems());
                    comboRiga2.getItems().addAll(
                            "OIII52",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158"
                    );
                }
                else if(comboFluxType2.getSelectionModel().getSelectedIndex() == 2) {
                    if(comboRiga2.getItems().size() != 0)
                        comboRiga2.getItems().removeAll(comboRiga2.getItems());
                    comboRiga2.getItems().addAll(
                            "SIV",
                            "NeII",
                            "NeV14.3",
                            "NeIII",
                            "SIII18.7",
                            "Nev24.3",
                            "OIV",
                            "SIII33.5",
                            "SiII"
                    );
                }
            }
        });
    }

    private Flux retrieveValueDB(String galaxy, String atom, String table) {
        FluxDAO fluxDAO = new FluxDAO();
        Flux result = fluxDAO.retrieveValFluxDB(galaxy, atom, table);
        fluxDAO.closeConnection();
        return result;
    }

    private String parseCombo(ComboBox combo) {

        if(combo.getSelectionModel().getSelectedIndex() == 0) {
            return "flussorighehp";
        }
        else if(combo.getSelectionModel().getSelectedIndex() == 1) {
            return "flussocontinuo";
        }
        else if(combo.getSelectionModel().getSelectedIndex() == 2) {
            return "flussorighesp";
        }
        else
            return null;
    }
}

package Control;

import Helper.ComboUtil;
import Helper.FluxDAO;
import Helper.GalaxyDAO;
import Helper.PsqlDBHelper;
import Model.Flux;
import View.FluxCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.smartcardio.CommandAPDU;
import java.util.ArrayList;

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
    private Label lblRapporto;
    @FXML
    private Label lblLimit;
    @FXML
    private ComboBox comboFluxType1;
    @FXML
    private ComboBox comboFluxType2;
    @FXML
    private ComboBox comboRigheHP1;
    @FXML
    private ComboBox comboContinuo1;
    @FXML
    private ComboBox comboRigheSp1;
    @FXML
    private ComboBox comboRigheHP2;
    @FXML
    private ComboBox comboContinuo2;
    @FXML
    private ComboBox comboRigheSp2;
    @FXML
    private Button btnCerca;

    private String primaRiga;
    private String secondaRiga;
    private String fluxType1;
    private String fluxType2;

    @FXML
    public void initialize() {

        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String galassia = txtGalassia.getText();

                String tipoFlusso1 = ComboUtil.parseCombo(comboFluxType1);
                String tipoFlusso2 = ComboUtil.parseCombo(comboFluxType2);

                if (tipoFlusso1 != null && tipoFlusso2 != null && primaRiga != null && secondaRiga != null &&
                        fluxType1.equals(tipoFlusso1) && fluxType2.equals(tipoFlusso2)) {
                    Flux val1 = retrieveValueDB(galassia, primaRiga, tipoFlusso1);
                    Flux val2 = retrieveValueDB(galassia, secondaRiga, tipoFlusso2);

                    if (val1 != null && val2 != null) {
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
                        alert.setContentText("Non sono state trovate corrispondenze rispetto ai criteri immessi");
                        alert.showAndWait();
                    }
                }
            }
        });
        btnCerca.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtGalassia.getText() != null) {
                    updateCombo(comboFluxType1, comboRigheHP1, comboContinuo1, comboRigheSp1);
                    updateCombo(comboFluxType2, comboRigheHP2, comboContinuo2, comboRigheSp2);
                }
            }
        });
        comboFluxType1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fluxType1 = (String)comboFluxType1.getSelectionModel().getSelectedItem();
            }
        });
        comboFluxType2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fluxType2 = (String)comboFluxType2.getSelectionModel().getSelectedItem();
            }
        });
        comboRigheHP1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaRiga = (String)comboRigheHP1.getSelectionModel().getSelectedItem();
            }
        });
        comboContinuo1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaRiga = (String)comboContinuo1.getSelectionModel().getSelectedItem();
            }
        });
        comboRigheSp1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaRiga = (String)comboRigheSp1.getSelectionModel().getSelectedItem();
            }
        });
        comboRigheHP2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaRiga = (String)comboRigheHP2.getSelectionModel().getSelectedItem();
            }
        });
        comboContinuo2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaRiga = (String)comboContinuo2.getSelectionModel().getSelectedItem();
            }
        });
        comboRigheSp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaRiga = (String)comboRigheSp2.getSelectionModel().getSelectedItem();
            }
        });
    }


    private void updateCombo(ComboBox comboFluxType, ComboBox comboRigheHP, ComboBox comboContinuo, ComboBox comboRigheSp) {
        FluxDAO fluxDAO = new FluxDAO();

        if (comboFluxType.getItems().size() != 0)
            comboFluxType.getItems().removeAll(comboFluxType.getItems());
        ArrayList<Flux> list = fluxDAO.retrieveAllFluxForGalaxyName(txtGalassia.getText(), "flussorighehp");
        int i = 0;
        if (comboRigheHP.getItems().size() != 0)
            comboRigheHP.getItems().removeAll(comboRigheHP.getItems());

        if (list.size() != 0) {
            comboFluxType.getItems().add("flussorighehp");
            while (i < list.size()) {
                comboRigheHP.getItems().add(list.get(i).getAtomo());
                //listAtomLineHP.add(list.get(i).getAtomo());
                i++;
            }
        }
        i = 0;
        list = fluxDAO.retrieveAllFluxForGalaxyName(txtGalassia.getText(), "flussocontinuo");
        if (comboContinuo.getItems().size() != 0)
            comboContinuo.getItems().removeAll(comboContinuo.getItems());
        if (list.size() != 0) {
            comboFluxType.getItems().add("flussocontinuo");
            while (i < list.size()) {
                comboContinuo.getItems().add(list.get(i).getAtomo());
                //listAtomLineHP.add(list.get(i).getAtomo());
                i++;
            }
        }
        i = 0;
        list = fluxDAO.retrieveAllFluxForGalaxyName(txtGalassia.getText(), "flussorighesp");
        if (comboRigheSp.getItems().size() != 0)
            comboRigheSp.getItems().removeAll(comboRigheSp.getItems());
        if (list.size() != 0) {
            comboFluxType.getItems().add("flussorighesp");
            while (i < list.size()) {
                //comboRiga1.getItems().add(list.get(i).getAtomo());
                comboRigheSp.getItems().add(list.get(i).getAtomo());
                i++;
            }
        }
    }

    public Flux retrieveValueDB(String galaxy, String atom, String table) {
        FluxDAO fluxDAO = new FluxDAO();
        Flux result = fluxDAO.retrieveValFluxDB(galaxy, atom, table);
        fluxDAO.closeConnection();
        return result;
    }


}

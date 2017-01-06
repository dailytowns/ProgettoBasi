package Control;

import Helper.FluxDAO;
import Model.Flux;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by feder on 06/01/2017.
 */
public class StatisticsValuesRatioFluxControl {

    @FXML
    private Label lblMediana;
    @FXML
    private Label lblValorMedio;
    @FXML
    private Label lblDevStand;
    @FXML
    private Label lblDevMediaAss;
    @FXML
    private ComboBox comboFlusso;
    @FXML
    private ComboBox comboGruppoSpettrale;
    @FXML
    private ComboBox comboAperture;
    @FXML
    private Button btnOK;


    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String fluxType = parseCombo(comboFlusso);
                String lineSelected = (String)comboGruppoSpettrale.getSelectionModel().getSelectedItem();
                FluxDAO fluxDAO = new FluxDAO();
                ArrayList<Double> list;

                if(comboAperture.getSelectionModel().getSelectedItem() != null) {
                    list = fluxDAO.retrieveValLineDB(fluxType, lineSelected, (String)comboAperture.getSelectionModel().getSelectedItem());
                } else {
                    list = fluxDAO.retrieveValLineDB(fluxType, lineSelected);
                }

                Double meanValue = computeMeanValue(list);
                Double median = computeMedian(list);
                Double devStand = computeDevStand(list, meanValue);
                Double devMedAss = computeDevMedAss(list, meanValue);

                lblValorMedio.setText(String.valueOf(meanValue));
                lblMediana.setText(String.valueOf(median));
                lblDevStand.setText(String.valueOf(devStand));
                lblDevMediaAss.setText(String.valueOf(devMedAss));
            }
        });

        comboFlusso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(comboFlusso.getSelectionModel().getSelectedIndex() == 0) {
                    if(comboGruppoSpettrale.getItems().size() != 0)
                        comboGruppoSpettrale.getItems().removeAll(comboGruppoSpettrale.getItems());
                    comboGruppoSpettrale.getItems().addAll(
                            "OIII52",
                            "NIII57",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158");

                    if(comboAperture.getItems().size() != 0)
                        comboAperture.getItems().removeAll(comboAperture.getItems());
                    comboAperture.getItems().addAll(
                            "c",
                            "3x3",
                            "5x5"
                    );
                }
                else if(comboFlusso.getSelectionModel().getSelectedIndex() == 1) {
                    if(comboGruppoSpettrale.getItems().size() != 0)
                        comboGruppoSpettrale.getItems().removeAll(comboGruppoSpettrale.getItems());
                    comboGruppoSpettrale.getItems().addAll(
                            "OIII52",
                            "OI63",
                            "OIII88",
                            "NII122",
                            "OI145",
                            "CII158"
                    );
                    if(comboAperture.getItems().size() != 0)
                        comboAperture.getItems().removeAll(comboAperture.getItems());
                    comboAperture.getItems().addAll(
                            "c",
                            "3x3",
                            "5x5"
                    );
                }
                else if(comboFlusso.getSelectionModel().getSelectedIndex() == 2) {
                    if(comboGruppoSpettrale.getItems().size() != 0)
                        comboGruppoSpettrale.getItems().removeAll(comboGruppoSpettrale.getItems());
                    comboGruppoSpettrale.getItems().addAll(
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
                    if(comboAperture.getItems().size() != 0)
                        comboAperture.getItems().removeAll(comboAperture.getItems());
                }
            }
        });

    }

    private Double computeDevMedAss(ArrayList<Double> list, Double meanValue) {
        int i;
        Double x, y, sum=0.0, res;

        for(i=0; i<list.size(); i++) {
            x = list.get(i) - meanValue;
            x = Math.abs(x);
            sum += x;
        }

        res = sum/(list.size());

        return res;
    }

    private Double computeDevStand(ArrayList<Double> list, Double meanValue) {

        int i;
        Double x, y, sum=0.0, res;

        for(i=0; i<list.size(); i++) {
            x = list.get(i) - meanValue;
            x = Math.pow(x, 2);
            sum += x;
        }

        res = sum/(list.size()-1);
        res = Math.sqrt(res);

        return res;
    }

    private Double computeMeanValue(ArrayList<Double> list) {

        Double numerator = 0.0;
        Double listSize = Double.valueOf(list.size());
        int i, j, k=0;

        for(i=0; i<list.size(); i++) {
            Double val1 = list.get(i);
            for(j=i+1; j<list.size(); j++) {
                numerator += val1/list.get(j);
                System.out.println(++k);
            }
        }

        return numerator/listSize;
    }

    private Double computeMedian(ArrayList<Double> list) {

        Collections.sort(list);
        return list.get(list.size()/2);

    }

    private String parseCombo(ComboBox combo) {

        if(combo.getSelectionModel().getSelectedItem().equals("flussorighehp")) {
            return "flussorighehp";
        }
        else if(combo.getSelectionModel().getSelectedItem().equals("flussocontinuo")) {
            return "flussocontinuo";
        }
        else if(combo.getSelectionModel().getSelectedItem().equals("flussorighesp")) {
            return "flussorighesp";
        }
        else
            return null;
    }

}

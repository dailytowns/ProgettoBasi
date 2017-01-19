package main.View;

import main.Model.FluxCellData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class FluxCell extends ListCell<FluxCellData> {

    @FXML
    private AnchorPane basePane;
    @FXML
    private Label lblNomeGalassia;
    @FXML
    private Label lblValore;
    @FXML
    private Label lblAtomo;
    @FXML
    private Label lblErrore;
    @FXML
    private Label lblUpperLimit;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(FluxCellData flux, boolean empty) {
        super.updateItem(flux, empty);

        if(empty || flux == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/ListCellFlux.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lblNomeGalassia.setText(String.valueOf(flux.getNomeGalassia()));
            lblAtomo.setText(flux.getAtomo());
            lblErrore.setText(String.valueOf(flux.getError()));
            lblValore.setText(String.valueOf(flux.getValore()));

            if(isUpperLimit(flux.getUpperLimit()))
                lblUpperLimit.setText("Sì");
            else
                lblUpperLimit.setText("No");

            setGraphic(basePane);
        }

    }

    private boolean isUpperLimit(String upperLimit) {

        if(upperLimit==null)
            return false;

        int i = 0;
        while (i<upperLimit.length()){
                if(upperLimit.charAt(i) == '<') {
                    return true;
                }
                i++;
            }
        return false;
    }

}

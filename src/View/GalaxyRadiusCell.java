package View;

import Model.GalaxyDataRadius;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * @author Federico Amici
 * Classe adibita alla presentazione dei dati sulle galassie
 * ordinati per raggio
 */
public class GalaxyRadiusCell extends ListCell<GalaxyDataRadius> {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblNumber;
    @FXML
    private AnchorPane basePane;
    @FXML
    private HBox hBox;
    @FXML
    private Label lblRadius;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(GalaxyDataRadius galaxy, boolean empty) {
        super.updateItem(galaxy, empty);

        if(empty || galaxy == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/GalaxyRadiusListCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lblNome.setText(String.valueOf(galaxy.getGalaxy().getName()));
            lblRadius.setText(String.valueOf(galaxy.getRadius()));
            lblNumber.setText(String.valueOf(galaxy.getCounter()));

            setGraphic(basePane);
        }

    }

}

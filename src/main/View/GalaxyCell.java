package main.View;

import main.Model.GalaxyData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;


public class GalaxyCell extends ListCell<GalaxyData> {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblNomeAlt;
    @FXML
    private Label lblRedshift;
    @FXML
    private Label lblNumber;
    @FXML
    private AnchorPane basePane;
    @FXML
    private HBox hBox;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(GalaxyData galaxy, boolean empty) {
        super.updateItem(galaxy, empty);

        if(empty || galaxy == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/ListCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lblNome.setText(String.valueOf(galaxy.getGalaxy().getName()));
            lblNomeAlt.setText(galaxy.getGalaxy().getAltName());
            lblRedshift.setText(String.valueOf(galaxy.getGalaxy().getRedshift()));
            lblNumber.setText(String.valueOf(galaxy.getCounter()));

            setGraphic(basePane);
        }

    }

}

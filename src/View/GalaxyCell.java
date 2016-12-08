package View;

import Model.Galaxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by feder on 07/12/2016.
 */
public class GalaxyCell extends ListCell<Galaxy> {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblNomeAlt;
    @FXML
    private Label lblRedshift;
    @FXML
    private AnchorPane basePane;
    @FXML
    private HBox hBox;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Galaxy galaxy, boolean empty) {
        super.updateItem(galaxy, empty);

        if(empty || galaxy == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/ListCell.fxml"));
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

            setGraphic(basePane);
        }

    }

}

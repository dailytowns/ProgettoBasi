package Control;

import Helper.GalaxyDAO;
import Helper.PsqlDBHelper;
import Model.Galaxy;
import Model.GalaxyData;
import View.GalaxyCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by feder on 25/12/2016.
 */
public class SearchGalaxyForRedshiftControl {

    @FXML
    private Button btnOK;
    @FXML
    private Label lblRicerca;
    @FXML
    private TextField txtRedshift;
    @FXML
    private ComboBox<String> choiceBox;
    @FXML
    private ListView listGalaxies;

    @FXML
    public void initialize() {

        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Double redshift = Double.valueOf(txtRedshift.getText());
                    String lgt = new String();
                    if(choiceBox.getSelectionModel().getSelectedIndex() == 0)
                        lgt = ">=";
                    else if (choiceBox.getSelectionModel().getSelectedIndex() == 1)
                        lgt = "<=";
                    ObservableList<GalaxyData> list = retrieveGalaxiesForRedshift(redshift, lgt);

                    if(list.size() != 0) {
                        listGalaxies.setItems(list);
                        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Info");
                        alert.setHeaderText(null);
                        alert.setContentText("Non sono state galassie corrispondenti ai criteri di ricerca");
                        alert.showAndWait();
                    }
            }
        });

    }

    public ObservableList<GalaxyData> retrieveGalaxiesForRedshift(Double redshiftValue, String lgt) {
        GalaxyDAO galaxyDAO = new GalaxyDAO();
        ObservableList<GalaxyData> obs = galaxyDAO.retrieveGalaxiesDB(redshiftValue, lgt);
        galaxyDAO.closeConnection();
        return obs;
    }

}

package main.Control;

import main.Helper.GalaxyDAO;
import main.Model.GalaxyData;
import main.View.ErrorGenericView;
import main.View.GalaxyCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Classe control della view di ricerca per redshift
 * @author Federico Amici
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
                    if(txtRedshift.getText().equals(""))
                        new ErrorGenericView("Immetti un valore di redshift");
                    else {
                        Double redshift = Double.valueOf(txtRedshift.getText());
                        String lgt = new String();
                        if (choiceBox.getSelectionModel().getSelectedIndex() == 0)
                            lgt = ">=";
                        else if (choiceBox.getSelectionModel().getSelectedIndex() == 1)
                            lgt = "<=";
                        ObservableList<GalaxyData> list = retrieveGalaxiesForRedshift(redshift, lgt);

                        if (list.size() != 0) {
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
            }
        });
        choiceBox.getSelectionModel().selectFirst();
    }

    public ObservableList<GalaxyData> retrieveGalaxiesForRedshift(Double redshiftValue, String lgt) {
        GalaxyDAO galaxyDAO = new GalaxyDAO();
        ObservableList<GalaxyData> obs = galaxyDAO.retrieveGalaxiesDB(redshiftValue, lgt);
        galaxyDAO.closeConnection();
        return obs;
    }

}

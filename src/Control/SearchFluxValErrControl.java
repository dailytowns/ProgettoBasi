package Control;

import Helper.PsqlDBHelper;
import Model.Galaxy;
import View.FluxCell;
import View.GalaxyCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by feder on 27/12/2016.
 */
public class SearchFluxValErrControl {

    @FXML
    private TextField txtGalaxy;
    @FXML
    private TextField txtAtoms;
    @FXML
    private ListView listFluxes;
    @FXML
    private Button btnOK;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String galaxy = txtGalaxy.getText();
                String[] atoms = txtAtoms.getText().split(";");

                ObservableList<Galaxy> list = retrieveValErrFlux(galaxy, atoms);
                listFluxes.setItems(list);
                listFluxes.setCellFactory(fluxCell -> new FluxCell());
            }
        });


    }

    private ObservableList retrieveValErrFlux(String galaxy, String[] atoms) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        ObservableList<Galaxy> obs = psqlDBHelper.retrieveValErrFluxDB(galaxy, atoms);
        psqlDBHelper.closeConnection();
        return obs;
    }


}

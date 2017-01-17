package Control;

import Helper.FluxDAO;
import Helper.PsqlDBHelper;
import Model.FluxCellData;
import Model.Galaxy;
import View.ErrorGenericView;
import View.FluxCell;
import View.GalaxyCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * Created by feder on 27/12/2016.
 */
public class SearchFluxValErrControl {

    @FXML
    private TextField txtGalaxy;
    @FXML
    private TextField txtAtoms;
    @FXML
    private ListView listFluxesRHP;
    @FXML
    private ListView listFluxesCont;
    @FXML
    private ListView listFluxesSpitzer;
    @FXML
    private Button btnOK;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String galaxy = txtGalaxy.getText();
                if(galaxy.equals(""))
                    new ErrorGenericView("Immetti il nome della galassia di cui vuoi fare la ricerca");
                else {
                    String[] atoms = txtAtoms.getText().split(";");
                    if(atoms[0].equals(""))
                        new ErrorGenericView("Immetti le righe di cui vuoi fare la ricerca");
                    else {
                        ObservableList<FluxCellData> list = retrieveValErrFlux(galaxy, atoms, "flussorighehp");
                        listFluxesRHP.setItems(list);
                        listFluxesRHP.setCellFactory(fluxCell -> new FluxCell());

                        list = retrieveValErrFlux(galaxy, atoms, "flussocontinuo");
                        listFluxesCont.setItems(list);
                        listFluxesCont.setCellFactory(fluxCell -> new FluxCell());

                        list = retrieveValErrFlux(galaxy, atoms, "flussorighesp");
                        listFluxesSpitzer.setItems(list);
                        listFluxesSpitzer.setCellFactory(fluxCell -> new FluxCell());
                    }
                }
            }
        });


    }

    private ObservableList retrieveValErrFlux(String galaxy, String[] atoms, String table) {
        FluxDAO fluxDAO = new FluxDAO();
        ObservableList<FluxCellData> obs = fluxDAO.retrieveValErrFluxDB(galaxy, atoms, table);
        fluxDAO.closeConnection();
        return obs;
    }


}

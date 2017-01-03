package Control;

import Helper.*;
import Model.Galaxy;
import Model.GalaxyData;
import View.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * @author Federico Amici
 * La classe si occupa di associare i listener alla view
 * di un utente registrato
 */
public class UserViewControl {

    @FXML
    private MenuItem menuRicercaGalassiaPerNome;
    @FXML
    private ListView listGalaxies;
    @FXML
    private ScrollPane scrollGalaxies;
    @FXML
    private MenuItem menuRicercaGalassiaPerRedshift;
    @FXML
    private MenuItem menuRicercaGalassiaPerRaggio;
    @FXML
    private MenuItem menuRicercaValErrFlusso;
    @FXML
    private MenuItem menuRapporti;

    @FXML
    public void initialize() {
        ObservableList<GalaxyData> list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());

        menuRicercaGalassiaPerNome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchGalaxyForNameView searchGalaxyForNameView = new SearchGalaxyForNameView();
            }
        });

        menuRicercaGalassiaPerRedshift.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchGalaxyForRedshiftView searchGalaxyForRedshiftView = new SearchGalaxyForRedshiftView();
            }
        });

        menuRicercaGalassiaPerRaggio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchGalaxyForRadiusView searchGalaxyForRadius = new SearchGalaxyForRadiusView();
            }
        });

        menuRicercaValErrFlusso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchFluxValErrView searchFluxValErrView = new SearchFluxValErrView();
            }
        });

        menuRapporti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchRatioFluxView searchRatioFluxView = new SearchRatioFluxView();
            }
        });

        scrollGalaxies.setFitToHeight(true);
        scrollGalaxies.setFitToWidth(true);

        /*ObservableList<Galaxy> list = FXCollections.observableArrayList();
        list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());*/
    }

    private ObservableList<GalaxyData> retrieveGalaxies() {
        GalaxyDAO galaxyDAO = new GalaxyDAO();
        ObservableList<GalaxyData> obs = galaxyDAO.retrieveGalaxiesDB();
        galaxyDAO.closeConnection();
        return obs;
    }

}

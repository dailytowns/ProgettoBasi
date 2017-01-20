package main.Control;


import main.Helper.*;
import main.Model.GalaxyData;
import main.View.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**Classe main.Control che gestisce la main.View di un utente amministratore
 * @author Federico Amici
 */
public class AdminControl extends UserViewControl{

    @FXML
    private MenuItem menuImporta;
    @FXML
    private MenuItem menuRegistra;
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
    private MenuItem menuStatiRatio;
    @FXML
    private Button btnDump;
    @FXML
    private Button btnResetUtenti;
    @FXML
    private MenuItem menuListUser;

    /**
     * Il metodo inizializza tutti i nodi della view cui deve essere
     * associato un listener. Vengono inoltre recuperati i dati utili
     * alla composizione della lista
     */
    @FXML
    public void initialize() {
        ObservableList<GalaxyData> list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());

        menuRegistra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InsertUserView insertUserView = new InsertUserView();
            }
        });

        menuListUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ListUserView();
            }
        });

        menuImporta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                ImportCSV importCSV;
                PsqlDBHelper psqlDBHelper;

                boolean chosen = false;

                List<File> listOfFile = fileChooser.showOpenMultipleDialog(stage);

                    if(listOfFile != null && listOfFile.size() == 1) {

                        switch(listOfFile.get(0).getName()) {
                            case "MRTable3_sample.csv":
                                importCSV = new ImportCSVGalaxy();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                ObservableList<GalaxyData> listNew = retrieveGalaxies();
                                listGalaxies.setItems(listNew);
                                listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());
                                listGalaxies.setVisible(true);
                                break;
                            case "MRTable4_flux.csv":
                                importCSV = new ImportCSVFluxLineHP();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                break;
                            case "MRTable6_cont.csv":
                                importCSV = new ImportCSVContFlux();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                break;
                            case "MRTable8_irs.csv":
                                importCSV = new ImportCSVFluxLineSpitzer();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                break;
                            case "MRTable11_C_3x3_5x5_flux.csv":
                                importCSV = new ImportCSVFluxLineHP();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                break;

                        }
                    } else {
                    /*L'utente ha cliccato su Annulla*/
                        System.out.println("Lista vuota");
                    }
                }

        });

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

        menuStatiRatio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatiRatioFluxView();
            }
        });

        btnResetUtenti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                (new ImportCSVUser()).importFile("C:\\Users\\feder\\Desktop\\UserFile.csv");
            }
        });

        btnDump.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                (new ImportCSVGalaxy()).importFile("C:\\Users\\feder\\Desktop\\MRTable3_Sample.csv");
                (new ImportCSVContFlux()).importFile("C:\\Users\\feder\\Desktop\\MRTable6_cont.csv");
                (new ImportCSVFluxLineHP()).importFile("C:\\Users\\feder\\Desktop\\MRTable4_flux.csv");
                (new ImportCSVFluxLineSpitzer()).importFile("C:\\Users\\feder\\Desktop\\MRTable8_irs.csv");
                ObservableList<GalaxyData> listNew = retrieveGalaxies();
                listGalaxies.setItems(listNew);
                listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());
                listGalaxies.setVisible(true);
            }
        });

        btnResetUtenti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserDAO userDAO = new UserDAO();
                int i;
                for(i=0; i<150; i++) {

                }
            }
        });

        scrollGalaxies.setFitToHeight(true);
        scrollGalaxies.setFitToWidth(true);

        /*ObservableList<Galaxy> list = FXCollections.observableArrayList();
        list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());*/
    }

    /**
     * Il metodo recupera galassie dal database e le organizza in
     * un oggetto di tipo ObservableList
     * @return La lista recuperata dal database
     */
    private ObservableList<GalaxyData> retrieveGalaxies() {
        GalaxyDAO galaxyDAO = new GalaxyDAO();
        ObservableList<GalaxyData> obs = galaxyDAO.retrieveGalaxiesDB();
        galaxyDAO.closeConnection();
        return obs;
    }

}

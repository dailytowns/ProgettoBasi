package Control;


import Helper.ImportCSV;
import Helper.ImportCSVFlux;
import Helper.ImportCSVGalaxy;
import Helper.PsqlDBHelper;
import Model.Galaxy;
import View.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.StringJoiner;

/**
 * Created by feder on 05/12/2016.
 */
public class AdminControl {

    @FXML
    private VBox basePane;
    @FXML
    private MenuBar menuMain;
    @FXML
    private MenuItem menuImporta;
    @FXML
    private MenuItem menuRegistra;
    @FXML
    private MenuItem menuRicercaGalassiaPerNome;
    @FXML
    private Menu menuUtenti;
    @FXML
    private ListView listGalaxies;
    @FXML
    private ScrollPane scrollGalaxies;
    @FXML
    private MenuItem menuRicercaGalassiaPerRedshift;
    @FXML
    private MenuItem menuRicercaGalassiaPerRaggio;

    @FXML
    public void initialize() {
        ObservableList<Galaxy> list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());

        menuRegistra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InsertUserView insertUserView = new InsertUserView();
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
                                psqlDBHelper = new PsqlDBHelper();
                                importCSV = new ImportCSVGalaxy();
                                importCSV.importFile(listOfFile.get(0).getAbsolutePath());
                                ObservableList<Galaxy> list = retrieveGalaxies();
                                listGalaxies.setItems(list);
                                listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());
                                break;
                            case "MRTable4_Flux.csv":
                                psqlDBHelper = new PsqlDBHelper();
                                importCSV = new ImportCSVFlux();

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

        scrollGalaxies.setFitToHeight(true);
        scrollGalaxies.setFitToWidth(true);

        /*ObservableList<Galaxy> list = FXCollections.observableArrayList();
        list = retrieveGalaxies();

        listGalaxies.setItems(list);
        listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());*/
    }

    private ObservableList<Galaxy> retrieveGalaxies() {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        ObservableList<Galaxy> obs = psqlDBHelper.retrieveGalaxiesDB();
        psqlDBHelper.closeConnection();
        return obs;
    }

}

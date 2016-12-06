package Control;


import Helper.PsqlDBHelper;
import View.InsertUserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    private Menu menuUtenti;

    @FXML
    public void initialize() {
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

                boolean chosen = false;

                while (!chosen) {
                    List<File> list =
                            fileChooser.showOpenMultipleDialog(stage);
                    if (list.size() == 1) {
                        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
                        for (File file : list) {
                            if (file.getName().equals("MRTable3_sample.csv")) {
                                psqlDBHelper.importCSVGalaxies(file.getAbsolutePath());
                                chosen = true;
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Attenzione!");
                                alert.setHeaderText(null);
                                alert.setContentText("Scegli il file che contiene il catalogo delle galassie");
                                alert.showAndWait();
                            }
                        }
                    } else {
                    /*L'utente ha cliccato su Annulla*/
                        System.out.println("Lista vuota");
                        }
                    }
                }
        });
    }

}

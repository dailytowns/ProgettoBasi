package main.Control;

import main.Helper.GalaxyDAO;
import main.Model.*;
import main.View.ErrorGenericView;
import main.View.GalaxyExtendedCell;
import main.View.GalaxySelectedView;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

/**
 * Classe control della view di ricerca di una galassia
 * per nome
 * @author Federico Amici
 */
public class SearchGalaxyControl {

    @FXML
    private Button btnOK;
    @FXML
    private TextField txtGalaxyName;
    @FXML
    private ListView listGalaxies;

    ObservableList<Galaxy> obs = null;

    /**
     * Il metodo inizializza tutti i nodi della view cui deve essere
     * associato un listener.
     */
    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GalaxyDAO galaxyDAO = new GalaxyDAO();
                //Galaxy galaxy = galaxyDAO.searchGalaxyForName(txtGalaxyName.getText());

                try {
                    obs = galaxyDAO.searchGalaxyForName(txtGalaxyName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if(obs.size() != 0) {
                    listGalaxies.setItems(obs);
                    listGalaxies.setCellFactory(galaxyCell -> new GalaxyExtendedCell());
                } else {
                   new ErrorGenericView("Non sono state recuperate galassie\n in base ai criteri immessi");
                }
                galaxyDAO.closeConnection();
            }
        });
        listGalaxies.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 &&
                        (event.getTarget() instanceof LabeledText || ((AnchorPane) event.getTarget()).getChildren().size() > 0)) {
                    int i = listGalaxies.getSelectionModel()
                            .getSelectedIndex();
                    String name = obs.get(i).getName();
                    String altName = obs.get(i).getAltName();
                    Double redshift = obs.get(i).getRedshift();
                    CoordinateAngolari coordinateAngolari = obs.get(i).getCoordinateAngolari();
                    CaratteristicheFisiche caratteristicheFisiche = obs.get(i).getCaratteristicheFisiche();
                    Galaxy galaxy = new Galaxy(name, altName, redshift, caratteristicheFisiche, coordinateAngolari);
                    new GalaxySelectedView(galaxy);
                }
            }
        });
    }

}


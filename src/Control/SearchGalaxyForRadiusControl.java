package Control;

import Helper.GalaxyDAO;
import Helper.PsqlDBHelper;
import Model.*;
import View.ErrorGenericView;
import View.GalaxyCell;
import View.GalaxyRadiusCell;
import com.sun.corba.se.impl.util.RepositoryIdCache;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by feder on 26/12/2016.
 */
public class SearchGalaxyForRadiusControl {

    @FXML
    private TextField txtHoursAR;
    @FXML
    private TextField txtMinutesAR;
    @FXML
    private TextField txtSecondsAR;
    @FXML
    private TextField txtDegreesDec;
    @FXML
    private TextField txtMinutesDec;
    @FXML
    private TextField txtSecondsDec;
    @FXML
    private ComboBox<String> choiceBox;
    @FXML
    private TextField txtNumberOfGalaxies;
    @FXML
    private Button btnOK;
    @FXML
    private ListView listGalaxies;
    @FXML
    private TextField txtRadius;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        choiceBox.getSelectionModel().selectFirst();
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<GalaxyDataRadius> list = null;
                Declination declination = new Declination();
                RightAscension rightAscension = new RightAscension();

                /*****Parsing right ascension************/
                if(txtHoursAR.getText().equals("") || txtMinutesAR.getText().equals("")
                        || txtSecondsAR.getText().equals(""))
                    new ErrorGenericView("Immetti tutti i dati relativi all'ascensione retta");
                else {
                    Integer hoursAR = Integer.valueOf(txtHoursAR.getText());
                    Integer minutesAR = Integer.valueOf(txtMinutesAR.getText());
                    Double secondsAR = Double.valueOf(txtSecondsAR.getText());
                    rightAscension = new RightAscension(hoursAR, minutesAR, secondsAR);
                }
                /***************************************/

                /*****Parsing declination****************/
                String sign = new String();
                if(choiceBox.getSelectionModel().getSelectedIndex() == 0)
                    sign = "+";
                else if(choiceBox.getSelectionModel().getSelectedIndex() == 1)
                    sign = "-";
                if(txtDegreesDec.getText().equals("") || txtMinutesDec.getText().equals("") ||
                        txtSecondsDec.getText().equals(""))
                    new ErrorGenericView("Immetti tutti i dati relativi alla declinazione");
                else {
                    Integer degreesDec = Integer.valueOf(txtDegreesDec.getText());
                    Integer minutesDec = Integer.valueOf((txtMinutesDec.getText()));
                    Double secondsDec = Double.valueOf(txtSecondsDec.getText());
                    declination = new Declination(sign, degreesDec, minutesDec, secondsDec);
                }
                /*****************************************/

                int numberOfGalaxies = Integer.valueOf(txtNumberOfGalaxies.getText());
                double radius = Double.valueOf(txtRadius.getText());

                if(parseInput(rightAscension, declination)) {
                    list = retrieveGalaxiesForRadius(declination, rightAscension, numberOfGalaxies, radius);
                    listGalaxies.setItems(list);
                    listGalaxies.setCellFactory(galaxyCell -> new GalaxyRadiusCell());
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attenzione!");
                    alert.setHeaderText(null);
                    alert.setContentText("Le coordinate immesse non sono ammissibili");
                    alert.showAndWait();
                }
            }
        });

    }

    /*Implementa le RV11 e RV12. Statica per testing*/
    public boolean parseInput(RightAscension ra, Declination d) {
        if(ra.getHour() < 24 && ra.getMinute() < 60 && ra.getSeconds() < 3600 &&
                d.getDegrees() < 90 && d.getMinute() < 60 && d.getSeconds() < 3600)
            return true;
        else
            return false;
    }


    public ObservableList<GalaxyDataRadius> retrieveGalaxiesForRadius(Declination declination, RightAscension rightAscension,
                                                             int numberOfGalaxies, double radius) {
        GalaxyDAO galaxyDAO = new GalaxyDAO();
        ObservableList<GalaxyDataRadius> obs = galaxyDAO.retrieveGalaxiesDB(declination, rightAscension, numberOfGalaxies, radius);
        galaxyDAO.closeConnection();
        return obs;
    }

}

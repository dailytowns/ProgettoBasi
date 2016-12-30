package Control;

import Helper.PsqlDBHelper;
import Model.Declination;
import Model.Galaxy;
import Model.RightAscension;
import View.GalaxyCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.soap.Text;
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
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*****Parsing right ascension************/
                Integer hoursAR = Integer.valueOf(txtHoursAR.getText());
                Integer minutesAR = Integer.valueOf(txtMinutesAR.getText());
                Double secondsAR = Double.valueOf(txtSecondsAR.getText());
                RightAscension rightAscension = new RightAscension(hoursAR, minutesAR, secondsAR);
                /***************************************/

                /*****Parsing declination****************/
                String sign = new String();
                if(choiceBox.getSelectionModel().getSelectedIndex() == 0)
                    sign = "+";
                else if(choiceBox.getSelectionModel().getSelectedIndex() == 1)
                    sign = "-";
                Integer degreesDec = Integer.valueOf(txtDegreesDec.getText());
                Integer minutesDec = Integer.valueOf((txtMinutesDec.getText()));
                Double secondsDec = Double.valueOf(txtSecondsDec.getText());
                Declination declination = new Declination(sign, degreesDec, minutesDec, secondsDec);
                /*****************************************/

                int numberOfGalaxies = Integer.valueOf(txtNumberOfGalaxies.getText());
                double radius = Double.valueOf(txtRadius.getText());

                ObservableList<Galaxy> list = retrieveGalaxiesForRadius(declination, rightAscension, numberOfGalaxies, radius);
                listGalaxies.setItems(list);
                listGalaxies.setCellFactory(galaxyCell -> new GalaxyCell());
            }
        });

    }

    private ObservableList<Galaxy> retrieveGalaxiesForRadius(Declination declination, RightAscension rightAscension,
                                                             int numberOfGalaxies, double radius) {
        PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
        ObservableList<Galaxy> obs = psqlDBHelper.retrieveGalaxiesDB(declination, rightAscension, numberOfGalaxies, radius);
        psqlDBHelper.closeConnection();
        return obs;
    }

}

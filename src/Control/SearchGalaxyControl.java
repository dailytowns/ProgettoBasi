package Control;

import Helper.PsqlDBHelper;
import Model.Galaxy;
import Model.GalaxyData;
import View.GalaxyFoundView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by feder on 07/12/2016.
 */
public class SearchGalaxyControl {

    @FXML
    private Button btnOK;
    @FXML
    private TextField txtSearchGalaxy;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PsqlDBHelper psqlDBHelper = new PsqlDBHelper();
                GalaxyData galaxy = psqlDBHelper.searchGalaxyForName(txtSearchGalaxy.getText());

                if(galaxy!=null) {
                    new GalaxyFoundView(galaxy);
                }
            }
        });
    }

}

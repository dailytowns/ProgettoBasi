package Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by feder on 07/01/2017.
 */
public class ErrorMessageControl {

    @FXML
    private Button btnOK;
    @FXML
    private Label label;

    @FXML
    public void initialize() {
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnOK.getScene().getWindow().hide();
            }
        });
    }

    public void setLabel (String atom) {this.label.setText(atom);}

}

package main.Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Classe che gestisce la finestra di un messaggio di errore
 * derivante da errore nel recupero di righe nel database
 * @author Federico Amici
 */
public class ErrorMessageControl {

    @FXML
    private Button btnOK;
    @FXML
    private Label label;

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
                btnOK.getScene().getWindow().hide();
            }
        });
    }

    /**
     * Il metodo assegna alla label della finestra il testo passato
     * come parametro
     * @param atom La riga che ha generato errore
     */
    public void setLabel (String atom) {this.label.setText(atom);}

}

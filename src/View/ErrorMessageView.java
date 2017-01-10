package View;

import Control.ErrorMessageControl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import java.io.IOException;

/**
 * Created by feder on 07/01/2017.
 */
public class ErrorMessageView {

    public ErrorMessageView(String table, String atom) {
        FXMLLoader fxmlLoader = null;
        Parent root = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/fxml/ErrorMessageView.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ErrorMessageControl errorMessageControl = fxmlLoader.getController();
        errorMessageControl.setLabel("Non sono state recuperate righe nella \ntabella " + table + "\n relativamente all'atomo " + atom);

        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 640, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        new ErrorMessageView("doinv", "dnv");
    }

}

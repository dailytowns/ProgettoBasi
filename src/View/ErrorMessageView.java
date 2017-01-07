package View;

import Control.ErrorMessageControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by feder on 07/01/2017.
 */
public class ErrorMessageView {

    public ErrorMessageView(String table, String atom) {
        Parent root = null;
        FXMLLoader fxmlLoader = null;

        try {
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("/resources/fxml/ErrorMessageView.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ErrorMessageControl errorMessageControl = fxmlLoader.getController();
        errorMessageControl.setLblAtom(atom);
        errorMessageControl.setLblTable(table);

        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        new ErrorMessageView("doinv", "dnv");
    }

}

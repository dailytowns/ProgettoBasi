package View;

import Control.ErrorMessageControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by feder on 16/01/2017.
 */
public class ErrorGenericView {

    public ErrorGenericView(String str) {
        FXMLLoader fxmlLoader = null;
        Parent root = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("resources/fxml/ErrorMessageView.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ErrorMessageControl errorMessageControl = fxmlLoader.getController();
        errorMessageControl.setLabel(str);

        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 480, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

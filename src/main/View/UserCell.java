package main.View;

import main.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserCell extends ListCell<User> {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblCognome;
    @FXML
    private Label lblMail;
    @FXML
    private Label lblUserId;
    @FXML
    private Label lblPassword;
    @FXML
    private Button btnAggiorna;
    @FXML
    private Button btnCancella;
    @FXML
    private AnchorPane basePane;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if(empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/UserCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            lblNome.setText(user.getName());
            lblCognome.setText(user.getSurname());
            lblMail.setText(user.getEmail());
            lblUserId.setText(user.getUserId());
            lblPassword.setText(user.getPassword());

            setGraphic(basePane);
        }

    }

}

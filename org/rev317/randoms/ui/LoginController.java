package org.rev317.randoms.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.rev317.randoms.Login;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * User: Jeroen
 * Date: 21/02/14
 * Time: 02:23
 */
public class LoginController implements Initializable {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Login.username = usernameField.getText();
                Login.password = passwordField.getText();
            }
        });
    }
}

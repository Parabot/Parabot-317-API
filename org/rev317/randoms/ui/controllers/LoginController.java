package org.rev317.randoms.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.rev317.randoms.ui.LoginUI;

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

    private static String username;
    private static String password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                username = usernameField.getText();
                password = passwordField.getText();
                LoginUI.pStage.close();
            }
        });
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}

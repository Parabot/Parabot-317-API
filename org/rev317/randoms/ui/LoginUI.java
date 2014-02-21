package org.rev317.randoms.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * User: Jeroen
 * Date: 19/02/14
 * Time: 21:48
 */
public class LoginUI extends Application {

    public static Stage pStage;

    public static void main() {
        Platform.runLater(new Runnable() {
            public void run() {
                new LoginUI().start(new Stage());
            }
        });
    }

    //fx:controller="org.rev317.randoms.Controller"
    @Override
    public void start(final Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(LoginUI.class.getResource("fxml/login.fxml"));
            Pane page = (Pane) loader.load();
            Scene scene = new Scene(page);
            primaryStage.setTitle("Auto login settings");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            pStage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading login.fxml!");
            e.printStackTrace();
        }
    }
}

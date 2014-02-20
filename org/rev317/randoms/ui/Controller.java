package org.rev317.randoms.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

/**
 * User: Jeroen
 * Date: 19/02/14
 * Time: 23:47
 */
public class Controller implements Initializable {

    @FXML
    private Button submit;

    @FXML
    private Pane panel;

    public static LinkedHashMap<String, Boolean> randoms = new LinkedHashMap<>();

    private static ArrayList<ToggleButton> toggleButtons= new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (randoms.isEmpty()){
            for (String s : new String[]{"Bob's island", "Mysterious old man", "Sandwich lady", "Auto login"}){
                randoms.put(s, false);
            }
        }

        int i = 25;
        for (String s : randoms.keySet()){
            ToggleButton b = new ToggleButton(s);
            b.setLayoutY(i);
            b.setLayoutX(25);
            toggleButtons.add(b);
            panel.getChildren().add(b);
            if (randoms.get(s) == true){
                b.setSelected(true);
            }
            i += 50;
        }
        submit.setLayoutY(i += 25);
        panel.setPrefHeight(i + 25);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < toggleButtons.size(); i++){
                    randoms.put(toggleButtons.get(i).getText(), toggleButtons.get(i).isSelected());
                }
                RandomUI.pStage.close();
            }
        });
    }
}

package com.example.cityswift.client.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlFile));

        Stage stage;
        if (event.getSource() instanceof Node) {
            Node source = (Node) event.getSource();
            stage = (Stage) source.getScene().getWindow();
        } else {
            throw new IllegalArgumentException("Event source is not a Node");
        }

        stage.setScene(new Scene(parent));
        stage.show();
    }

}

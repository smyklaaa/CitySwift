package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ForgotPasswordController {

    @FXML
    private Label reactionMessageLabel;
    public void sendCodeButtonOn(ActionEvent event) {
    }


    public void goBackToLoginButtonOn(ActionEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/view/login/loginView.fxml");
        } catch (IOException e) {
            reactionMessageLabel.setText("Błąd ładowania sceny");
            System.out.println(e);
        }
    }
}

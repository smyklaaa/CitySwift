package com.example.cityswift.client.controller;


import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField mailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField mobileTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private DatePicker birthdayDataPicker;
    @FXML
    private Label reactionMessageLabel;


    public void signupButtonOn(ActionEvent event) {

        if (!mailTextField.getText().isBlank()
                && !passwordField.getText().isBlank()
                && !surnameTextField.getText().isBlank()
                && !nameTextField.getText().isBlank()
                && !mobileTextField.getText().isBlank()
                && !addressTextField.getText().isBlank()) {

            ServerResponse serverResponse = sendSignupRequest(nameTextField.getText(), surnameTextField.getText(),
                    mailTextField.getText(), passwordField.getText(), mobileTextField.getText(),
                    addressTextField.getText());

            handleSignupResponse(event, serverResponse);

        } else {
            reactionMessageLabel.setText("Wszystkie pola muszą być wypełnione");
        }
    }

    private ServerResponse sendSignupRequest(String name, String surname, String mail, String password,
                                             String mobile, String address) {
        NetworkClient networkClient = new NetworkClient();

        try {
            CreateUserData newUserData = new CreateUserData(name, surname, mail, password, mobile);
            ClientRequest request = new ClientRequest("signup", newUserData);

            return networkClient.sendRequest(request);

        } catch (Exception e) {
            reactionMessageLabel.setText("Błąd połączenia z serwerem");
        }

        return null;
    }

    public void handleSignupResponse(ActionEvent event, ServerResponse serverResponse) {
        if (serverResponse.getResultCode() == 200) {
            try {
                SceneSwitcher.switchScene(event, "/view/login/loginView.fxml");
            } catch (IOException e) {
                reactionMessageLabel.setText("Błąd ładowania sceny");
            }
        }
    }
}

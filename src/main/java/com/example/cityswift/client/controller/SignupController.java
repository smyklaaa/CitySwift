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
import java.time.LocalDate;

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
    private TextField postalCodeTextField;
    @FXML
    private TextField streetNameTextField;
    @FXML
    private TextField homeNrTextField;
    @FXML
    private TextField doorKeyTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private Label reactionMessageLabel;
    @FXML
    private TextField cityTextField;


    public void signupButtonOn(ActionEvent event) {

        if (!mailTextField.getText().isBlank()
                && !passwordField.getText().isBlank()
                && !surnameTextField.getText().isBlank()
                && !nameTextField.getText().isBlank()
                && !mobileTextField.getText().isBlank()
                && !postalCodeTextField.getText().isBlank()
                && !streetNameTextField.getText().isBlank()
                && !homeNrTextField.getText().isBlank()
                && !doorKeyTextField.getText().isBlank()
                && !cityTextField.getText().isBlank()
                && birthdayDatePicker.getValue() !=null ) {

            ServerResponse serverResponse = sendSignupRequest(nameTextField.getText(), surnameTextField.getText(),
                    mailTextField.getText(), passwordField.getText(), mobileTextField.getText(),
                    cityTextField.getText(), streetNameTextField.getText(), postalCodeTextField.getText(),homeNrTextField.getText(),
                    doorKeyTextField.getText(),birthdayDatePicker.getValue());

            handleSignupResponse(event, serverResponse);

        } else {
            reactionMessageLabel.setText("Wszystkie pola muszą być wypełnione");
        }
    }

    private ServerResponse sendSignupRequest(String name, String surname, String mail, String password,
                                             String mobile, String city, String street,String postalCode,String homeNr,
                                             String doorKeyNr, LocalDate birthday) {
        NetworkClient networkClient = new NetworkClient();

        try {
            CreateUserData newUserData = new CreateUserData(name, surname, mail, password, mobile,city,street,postalCode,
                    homeNr,doorKeyNr, birthday);
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

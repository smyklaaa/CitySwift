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
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private Label incorrectDataMessageLabel;


    public void signupButtonOn(ActionEvent event) {

        if (isFormValid()) {

            checkPasswordRequirements(passwordField.getText());

            ServerResponse serverResponse = sendSignupRequest(nameTextField.getText(), surnameTextField.getText(),
                    mailTextField.getText(), passwordField.getText(), mobileTextField.getText(),
                    cityTextField.getText(), streetNameTextField.getText(), postalCodeTextField.getText(),
                    homeNrTextField.getText(), doorKeyTextField.getText(), birthdayDatePicker.getValue());

            handleSignupResponse(event, serverResponse);

        } else {
            reactionMessageLabel.setText("Wszystkie pola muszą być wypełnione");
        }
    }

    private ServerResponse sendSignupRequest(String name, String surname, String mail, String password,
                                             String mobile, String city, String street, String postalCode,
                                             String homeNr, String doorKeyNr, LocalDate birthday) {
        NetworkClient networkClient = new NetworkClient();

        try {
            CreateUserData newUserData = new CreateUserData(name, surname, mail, password, mobile, city, street,
                    postalCode, homeNr, doorKeyNr, birthday);
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

    public boolean checkPasswordRequirements(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        Matcher matcher = pattern.matcher(password);

        if (password.length() < 8) {
            incorrectDataMessageLabel.setText("Twoje hasło zawiera mniej niz 8 znakow");
            passwordField.clear();
            return false;
        } else if (!matcher.matches()) {
            incorrectDataMessageLabel.setText("Twoje hasło nie zawiera min jednej małej litery," +
                    "\ndużej litery lub cyfry");
            passwordField.clear();
            return false;
        } else {
            incorrectDataMessageLabel.setText("");
            return true;
        }
    }

    public boolean checkPostalCodeRequirements(String postalCode) {

        if (postalCode.length() != 5) {
            incorrectDataMessageLabel.setText("Twój kod pocztowy ma nieodpowiednia długość");
            postalCodeTextField.clear();
            return false;
        } else if (!postalCode.matches("\\d+")) {
            incorrectDataMessageLabel.setText("Twój kod pocztowy zawiera inne znaki niż cyfry");
            postalCodeTextField.clear();
            return false;
        } else {
            incorrectDataMessageLabel.setText("");
            return true;
        }
    }

    public boolean checkMobileRequirements(String mobile) {

        if (mobile.length() != 9) {
            incorrectDataMessageLabel.setText("Twój nr telefonu ma nieodpowiednia długość");
            mobileTextField.clear();
            return false;
        } else if (!mobile.matches("\\d+")) {
            incorrectDataMessageLabel.setText("Twój nr telefonu zawiera inne znaki niż cyfry");
            mobileTextField.clear();
            return false;
        } else {
            incorrectDataMessageLabel.setText("");
            return true;
        }
    }

    public boolean checkMailRequirements(String mail) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(mail);

        if (!matcher.matches()) {
            incorrectDataMessageLabel.setText("Podałeś zły format maila");
            mailTextField.clear();
            return false;
        } else {
            incorrectDataMessageLabel.setText("");
            return true;
        }
    }


    public boolean checkIfUserOverEighteen(LocalDate date) {

        LocalDate currentDate = LocalDate.now();
        LocalDate ageLimitDate = currentDate.minusYears(18);

        if(date.isAfter(ageLimitDate)){
            incorrectDataMessageLabel.setText("Musisz mieć powyżej 18 lat aby się zarejstrować");
            return false;
        }else{
            incorrectDataMessageLabel.setText("");
            return true;
        }
    }

    public boolean  isFormValid(){
        if (!mailTextField.getText().isBlank()
                && checkMailRequirements(mailTextField.getText())
                && !passwordField.getText().isBlank()
                && checkPasswordRequirements(passwordField.getText())
                && !surnameTextField.getText().isBlank()
                && !nameTextField.getText().isBlank()
                && !mobileTextField.getText().isBlank()
                && checkMobileRequirements(mobileTextField.getText())
                && !postalCodeTextField.getText().isBlank()
                && checkPostalCodeRequirements(postalCodeTextField.getText())
                && !streetNameTextField.getText().isBlank()
                && !homeNrTextField.getText().isBlank()
                && !doorKeyTextField.getText().isBlank()
                && !cityTextField.getText().isBlank()
                && birthdayDatePicker.getValue() != null
                && checkIfUserOverEighteen(birthdayDatePicker.getValue())){
            return true;
        }else {
            return false;
        }
    }

    public void goToLoginPageButtonOn(ActionEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/view/login/loginView.fxml");
        } catch (IOException e) {
            reactionMessageLabel.setText("Błąd ładowania sceny");
            System.out.println(e);
        }
    }

}

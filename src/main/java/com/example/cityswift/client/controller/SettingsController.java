package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.AddressDTO;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.dto.UserCredential;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsController {

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField repeatPasswordTextField;
    @FXML
    private TextField mobileTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField streetNameTextField;
    @FXML
    private TextField homeNrTextField;
    @FXML
    private TextField doorKeyTextField;
    @FXML
    private Label reactionMessageLabel;
    @FXML
    private Label incorrectDataMessageLabel;

    public void changePasswordButtonOn() {
        if (!passwordTextField.getText().isBlank()
                && !repeatPasswordTextField.getText().isBlank()
                && checkPasswordRequirements(passwordTextField.getText(), repeatPasswordTextField.getText())) {

            ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest(
                    "changePassword", passwordTextField.getText(), UserSession.getUserToken().getToken()));


            if (serverResponse.getResultCode() == 200) {
                reactionMessageLabel.setText("Twoje hasło zostało pomyślnie zmnienione");
            } else reactionMessageLabel.setText("Błąd przy zmianie hasła");
        } else reactionMessageLabel.setText("Najpierw wypełnij pola");
    }

    public void changeMobileButtonOn() {
        if (!mobileTextField.getText().isBlank()
                && checkMobileRequirements(mobileTextField.getText())) {

            ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest(
                    "changeMobile", mobileTextField.getText(), UserSession.getUserToken().getToken()));


            if (serverResponse.getResultCode() == 200) {
                reactionMessageLabel.setText("Twój numer został pomyślnie zmnieniony");
            } else reactionMessageLabel.setText("Błąd przy zmianie numeru");
        } else reactionMessageLabel.setText("Najpierw wypełnij pola");
    }

    public void changeAddressButtonOn() {
        if (!cityTextField.getText().isBlank()
                && !postalCodeTextField.getText().isBlank()
                && !streetNameTextField.getText().isBlank()
                && !homeNrTextField.getText().isBlank()
                && !doorKeyTextField.getText().isBlank()
                && checkPostalCodeRequirements(postalCodeTextField.getText())) {

            AddressDTO newAddress = new AddressDTO(postalCodeTextField.getText(),
                    cityTextField.getText(),
                    streetNameTextField.getText(),
                    homeNrTextField.getText(),
                    doorKeyTextField.getText());

            ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest(
                    "changeAddress", newAddress, UserSession.getUserToken().getToken()));

            if (serverResponse.getResultCode() == 200) {
                reactionMessageLabel.setText("Twój adres został pomyślnie zmnieniony");
            } else reactionMessageLabel.setText("Błąd przy zmianie adresu");
        } else reactionMessageLabel.setText("Najpierw wypełnij pola");
    }


    public boolean checkPasswordRequirements(String password, String repeatedPassword) {

        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        Matcher matcher = pattern.matcher(password);

        if (password.length() < 8) {
            incorrectDataMessageLabel.setText("Twoje hasło zawiera mniej niz 8 znakow");
            passwordTextField.clear();
            repeatPasswordTextField.clear();
            return false;
        } else if (!matcher.matches()) {
            incorrectDataMessageLabel.setText("Twoje hasło nie zawiera min jednej małej litery," +
                    "\ndużej litery lub cyfry");
            passwordTextField.clear();
            repeatPasswordTextField.clear();

            return false;
        } else if (!password.equals(repeatedPassword)) {
            incorrectDataMessageLabel.setText("Hasła nie są takie same");
            passwordTextField.clear();
            repeatPasswordTextField.clear();

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
}

package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.dto.UserCredential;
import com.example.cityswift.server.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordController {

    @FXML
    private Label reactionMessageLabel;
    @FXML
    private TextField mailTextField;
    @FXML
    private Label errorReactionMessageLabel;


    public void sendCodeButtonOn(ActionEvent event) {
        if(!mailTextField.getText().isBlank() && checkMailRequirements(mailTextField.getText())){
            UserModel userModel = new UserModel((mailTextField.getText()));

            ServerResponse serverResponseForgotPassword = NetworkClient.sendRequest(new ClientRequest(
                    "forgotPassword", userModel));
            if(serverResponseForgotPassword.getResultCode() == 200){
                reactionMessageLabel.setText("Kod został pomyślnie wysłany");
            }else if(serverResponseForgotPassword.getResultCode() == 404) {
                reactionMessageLabel.setText("Nie ma zarejstrowanego użytkownika z podanym adresem ");
            }else reactionMessageLabel.setText(""+serverResponseForgotPassword.getResultCode());
        }else{
            reactionMessageLabel.setText("Podaj adres e-mail");
        }

    }

    public void goBackToLoginButtonOn(ActionEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/view/login/loginView.fxml");
        } catch (IOException e) {
            reactionMessageLabel.setText("Błąd ładowania sceny");
            System.out.println(e);
        }
    }

    public boolean checkMailRequirements(String mail) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(mail);

        if (!matcher.matches()) {
            errorReactionMessageLabel.setText("Podałeś zły format maila");
            mailTextField.clear();
            return false;
        } else {
            errorReactionMessageLabel.setText("");
            return true;
        }
    }
}

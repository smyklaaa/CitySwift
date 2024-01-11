package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.dto.UserCredential;
import com.example.cityswift.dto.UserToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class FirstPageController {

    @FXML
    private Label ReactionMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void loginButtonOn(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            ReactionMessageLabel.setText("Próba logowania");
            ServerResponse serverResponse = sendLoginRequest(usernameTextField.getText(), passwordTextField.getText());
            handleUserLoginResponse(serverResponse, event);
        } else {
            ReactionMessageLabel.setText("Podaj login oraz hasło");
        }
    }

    private void handleUserLoginResponse(ServerResponse serverResponse, ActionEvent event) {
        if(serverResponse.getResultCode()==200) {
            try {
                UserSession.setUserToken((UserToken) serverResponse.getData());
                SceneSwitcher.switchScene(event, "/view/dashboard/dashboardView.fxml");
            } catch (IOException e) {
                ReactionMessageLabel.setText("Błąd ładowania sceny");
            }
        } else {
            ReactionMessageLabel.setText("Niepoprawny login lub hasło");
        }
    }

    private ServerResponse sendLoginRequest(String username, String password) {
        NetworkClient networkClient = new NetworkClient();

        try {
            UserCredential credentials = new UserCredential(username, password);
            ClientRequest request = new ClientRequest("login", credentials);

            return networkClient.sendRequest(request);

        } catch (Exception e) {
            ReactionMessageLabel.setText("Błąd połączenia z serwerem");
        }

        return null;
    }

    public void signupButtonOn(ActionEvent event) {
        ReactionMessageLabel.setText("Próba rejstracji");
        try {
            SceneSwitcher.switchScene(event, "/view/signup/signupView.fxml");
        } catch (IOException e) {
            ReactionMessageLabel.setText("Błąd ładowania sceny");
            System.out.println(e);
        }


    }

    public void forgotPasswordButtonOn(ActionEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/view/forgotPassword/forgotPasswordView.fxml");
        } catch (IOException e) {
            ReactionMessageLabel.setText("Błąd ładowania sceny");
            System.out.println(e);
        }
    }


}

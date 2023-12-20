package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {
    @FXML
    private AnchorPane routerPane;

    @FXML
    private Label panelName;

    @FXML
    private void loadHome() {
        loadContent("/view/home/homeView.fxml", "Home");
    }

    @FXML
    private void loadFriends() {
        loadContent("/view/friends/friendsView.fxml", "Znajomi");
    }

    @FXML
    private void loadSettings() {
        loadContent("/view/settings/settingsView.fxml", "Ustawienia");
    }

    @FXML
    private void initialize() {
        loadContent("/view/home/homeView.fxml", "Home");
    }

    private void loadContent(String fxml, String panelNameText) {
        try {
            routerPane.getChildren().clear();
            Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            routerPane.getChildren().add(node);
            panelName.setText(panelNameText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

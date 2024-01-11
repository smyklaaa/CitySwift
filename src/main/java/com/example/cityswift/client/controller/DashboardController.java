package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.Objects;

public class DashboardController {
    @FXML
    private AnchorPane routerPane;

    @FXML
    private Label panelName;

    @FXML
    private void loadHome() {
        loadContent("/view/home/homeView.fxml", "Strona główna");
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
    private void loadOrderHistory() {
        loadContent("/view/orderHistory/orderHistoryView.fxml",
                "Historia zamówień");
    }

    @FXML
    private void loadSendPackage() {
        loadContent("/view/sendPackage/sendPackageView.fxml",
                "Nadaj Paczkę");
    }

    @FXML
    private void loadLogout(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "/view/login/loginView.fxml");
    }

    @FXML
    private void loadBecomeCourier() {
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getCourierCurrentOrder", null, UserSession.getUserToken().getToken()));
        if (serverResponse.getResultCode() == 200) {
            loadContent("/view/becomeCourier/currentOrder.fxml",
                    "Zostań kurierem");
        } else{
            loadContent("/view/becomeCourier/becomeCourierView.fxml",
                    "Zostań kurierem");
        }


    }

    @FXML
    private void initialize() {
        loadContent("/view/home/homeView.fxml", "Home");
    }

    public void loadContent(String fxml, String panelNameText) {
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

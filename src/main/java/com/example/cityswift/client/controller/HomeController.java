package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label birthLabel;
    @FXML
    private Label mobileLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label codeLabel;
    @FXML
    private Label homeNumberLabel;

    @FXML
    private void initialize() {
        ServerResponse userBasicData = NetworkClient.sendRequest(new ClientRequest("getUserBasicData", UserSession.getUserToken().getToken()));
        BasicUserData basicUserData = (BasicUserData) userBasicData.getData();
        setBasicUserData(basicUserData);
    }

    private void setBasicUserData(BasicUserData basicUserData) {
        nameLabel.setText(basicUserData.getUserModel().getFirstName());
        surnameLabel.setText(basicUserData.getUserModel().getLastName());
        birthLabel.setText(String.valueOf(basicUserData.getUserModel().getDateOfBirth()));
        mobileLabel.setText(basicUserData.getUserModel().getMobile());
        if (basicUserData.getAddressModel().isEmpty()) {
            streetLabel.setText("Brak danych");
            cityLabel.setText("Brak danych");
            codeLabel.setText("Brak danych");
            homeNumberLabel.setText("Brak danych");
        } else {
            streetLabel.setText(basicUserData.getAddressModel().get(0).getStreet());
            codeLabel.setText(basicUserData.getAddressModel().get(0).getPostalCode());
            homeNumberLabel.setText(basicUserData.getAddressModel().get(0).getHomeNumber());
        }
    }

}

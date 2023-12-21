package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class FriendsController {
    @FXML
    private TextField friendsTextField;

    @FXML
    private ListView<String> userListView;

    @FXML
    private void searchFriends() {
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("searchUser", friendsTextField.getText()));
        List<BasicUserData> usersFound = (List<BasicUserData>) serverResponse.getData();

        ObservableList<String> userDisplayList = FXCollections.observableArrayList();

        for (BasicUserData basicUserData : usersFound) {
            String displayText =  basicUserData.getUserModel().getFirstName() + " " + basicUserData.getUserModel().getLastName();
            userDisplayList.add(displayText);
        }
        userListView.setItems(userDisplayList);
    }

}

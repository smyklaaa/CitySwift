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
    private ListView<String> requestList;

    @FXML
    private void searchFriends() {
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("searchUser", friendsTextField.getText(), UserSession.getUserToken().getToken()));
        List<BasicUserData> usersFound = (List<BasicUserData>) serverResponse.getData();

        ObservableList<String> userDisplayList = FXCollections.observableArrayList();

        if(usersFound != null){
            for (BasicUserData basicUserData : usersFound) {
                String displayText = basicUserData.getUserModel().getPublicToken() + " " + basicUserData.getUserModel().getFirstName() + " " + basicUserData.getUserModel().getLastName();
                userDisplayList.add(displayText);
            }
            userListView.setItems(userDisplayList);
        }
    }

    @FXML
    private void addFriend(){
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        String[] selectedUserSplit = selectedUser.split(" ");
        String friendPublicToken = selectedUserSplit[0];

        NetworkClient.sendRequest(new ClientRequest("addFriend", friendPublicToken, UserSession.getUserToken().getToken()));
    }

    @FXML
    private void acceptFriend(){
        String selectedUser = requestList.getSelectionModel().getSelectedItem();
        String[] selectedUserSplit = selectedUser.split(" ");
        String friendPublicToken = selectedUserSplit[0];

        NetworkClient.sendRequest(new ClientRequest("acceptFriend", friendPublicToken, UserSession.getUserToken().getToken()));
        updateFriendRequestList();
    }

    @FXML
    private void initialize(){
        updateFriendRequestList();
    }


    private void updateFriendRequestList(){
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getFriends", false, UserSession.getUserToken().getToken()));
        List<BasicUserData> friends = (List<BasicUserData>) serverResponse.getData();

        ObservableList<String> userDisplayList = FXCollections.observableArrayList();

        for (BasicUserData basicUserData : friends) {
            String displayText = basicUserData.getUserModel().getPublicToken() + " " + basicUserData.getUserModel().getFirstName() + " " + basicUserData.getUserModel().getLastName();
            userDisplayList.add(displayText);
        }
        requestList.setItems(userDisplayList);
    }



}

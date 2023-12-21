package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class OrderHistoryController {
    @FXML
    private TextField orderHistorySerchTextField;
    @FXML
    private Button orderHistorySerchButton;
    @FXML
    private ListView<String> currentPacagesListView;

    @FXML
    private ListView<String> historyOfPackageListView;


    public void searchPackageFriends(ActionEvent event) {
    }
}

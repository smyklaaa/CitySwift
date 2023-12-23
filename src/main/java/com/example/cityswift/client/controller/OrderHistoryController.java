package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.service.OrderService;
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

    public void getListOfPackages(){
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getUserOrdersHistory",
               null, UserSession.getUserToken().getToken()));

        List<OrderModel> orders = (List<OrderModel>) serverResponse.getData();

        for(OrderModel order :orders){
            String status = getStatusDescription(order.getStatusId());
            String showData = "id:"+order.getId()+"    id nadawcy:"+order.getSenderId()+"    id odbiorcy:"+order.getRecipientId()
                    +"    id kuriera:"+order.getCourierId()+"    cena zł:"+ order.getPrice()+"    id paczki:"+order.getPackageId()
                    +"    status:"+status+"    wiadomość:"+order.getMessage();
            if(order.getStatusId() == 1 || order.getStatusId() == 2){
                currentPacagesListView.getItems().add(showData);
            } else {
                historyOfPackageListView.getItems().add(showData);
            }
        }

    }

    public String getStatusDescription(int status){
        if(status == 1){
            return "Oczekiwanie na kuriera";
        } else if (status == 2) {
            return "W trakcie dostawy";
        }else if(status == 3){
            return "Dostarczona";
        }else return"Błąc odczytu";
    }


    @FXML
    public void initialize(){
        getListOfPackages();
    }
}

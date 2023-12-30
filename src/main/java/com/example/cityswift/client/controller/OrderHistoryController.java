package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.OrderModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


import java.util.List;

public class OrderHistoryController {

    @FXML
    private ListView<String> sendPackagesListView;

    @FXML
    private ListView<String> receivedPackageListView;


    public void searchPackageFriends(ActionEvent event) {

    }

    public void displayListOfPackages(){
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getUserOrdersHistory",
               null, UserSession.getUserToken().getToken()));

        List<OrderModel> orders = (List<OrderModel>) serverResponse.getData();
        showRecipientOrders(orders);
    }

    public  void showRecipientOrders(List<OrderModel> orders){
        for(OrderModel order :orders){
            String status = getStatusDescription(order.getStatusId());
            String showData = "Nadawca: "+order.getFirstName()+" "+order.getLastName()
                    +"    Id kuriera: "+order.getCourierId()
                    +"    Cena zł: "+ order.getPrice()
                    +"    Id paczki: "+order.getPackageId()
                    +"    Status: "+status
                    +"    Wiadomość: "+order.getMessage();


            receivedPackageListView.getItems().add(showData);
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
        displayListOfPackages();
    }
}

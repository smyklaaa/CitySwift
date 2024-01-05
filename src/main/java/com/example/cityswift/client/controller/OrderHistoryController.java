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
    private static final int RECEIVED_ORDERS = 1;
    private static final int SEND_ORDERS = 2;


    public void searchPackageFriends(ActionEvent event) {

    }

    public void displayListOfPackages() {
        ServerResponse serverResponseReceivedOrders = NetworkClient.sendRequest(new ClientRequest(
                "getUserReceivedOrdersHistory",
                null, UserSession.getUserToken().getToken()));

        ServerResponse serverResponseSendOrders = NetworkClient.sendRequest(new ClientRequest(
                "getUserSendOrdersHistory",
                null, UserSession.getUserToken().getToken()));

        List<OrderModel> receivedOrders = (List<OrderModel>) serverResponseReceivedOrders.getData();
        List<OrderModel> sendOrders = (List<OrderModel>) serverResponseSendOrders.getData();

        showOrders(receivedOrders, RECEIVED_ORDERS);
        showOrders(sendOrders,SEND_ORDERS);
    }

    public void showOrders(List<OrderModel> orders, int whichOrders) {
        for (OrderModel order : orders) {
            String status = getStatusDescription(order.getStatusId());
            String showData = "    Id kuriera: " + order.getCourierId()
                    + "    Cena zł: " + order.getPrice()
                    + "    Id paczki: " + order.getPackageId()
                    + "    Status: " + status
                    + "    Wiadomość: " + order.getMessage();

            if (whichOrders == 1) {
                receivedPackageListView.getItems().add("Nadawca: " + order.getFirstName() + " " + order.getLastName()
                        + showData);
            } else if (whichOrders == 2) {
                sendPackagesListView.getItems().add("Mail odbiorcy: "+ order.getMail()+ showData);
            }

        }
    }


    public String getStatusDescription(int status) {
        if (status == 1) {
            return "Oczekiwanie na kuriera";
        } else if (status == 2) {
            return "W trakcie dostawy";
        } else if (status == 3) {
            return "Dostarczona";
        } else return "Błąc odczytu";
    }


    @FXML
    public void initialize() {
        displayListOfPackages();
    }
}

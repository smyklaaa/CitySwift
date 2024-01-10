package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.EndDeliveryData;
import com.example.cityswift.dto.OrderDetailsDTO;
import com.example.cityswift.dto.ServerResponse;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CurrentOrderController {

    @FXML
    TextField orderCodeInput;

    @FXML
    Label fromAddress;

    @FXML
    Label toAddress;

    @FXML
    Label recipientMobile;

    @FXML
    Label senderMobile;

    @FXML
    Label price;

    @FXML
    Label senderDetails;

    @FXML
    Label orderCodeError;

    @FXML
    private void cancelOrder() {
        NetworkClient.sendRequest(new ClientRequest("cancelOrder", currentOrderId, UserSession.getUserToken().getToken()));
    }

    @FXML
    private void sendDeliveryAlert() {
        NetworkClient.sendRequest(new ClientRequest("sendDeliveryNotification", currentOrderId, UserSession.getUserToken().getToken()));
    }

    @FXML
    private void finishOrder(ActionEvent event) {
        ClientRequest endDelivery = new ClientRequest("endDelivery", new EndDeliveryData(
                Integer.parseInt(orderCodeInput.getText()),
                currentOrderId
        ), UserSession.getUserToken().getToken());
            ServerResponse serverResponse = NetworkClient.sendRequest(endDelivery);
        if (serverResponse.getResultCode() == 200) {
            orderCodeError.setText("Podany kod jest poprawny");
        } else {
            orderCodeError.setText("Podano zły kod");
        }
    }

    private int currentOrderId;

    @FXML
    private void initialize() {
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getCourierCurrentOrder", null, UserSession.getUserToken().getToken()));
        OrderDetailsDTO orderDetailsDTO = (OrderDetailsDTO) serverResponse.getData();
        currentOrderId = Integer.parseInt(orderDetailsDTO.getOrderId());
        toAddress.setText(orderDetailsDTO.getRecipientCity() + " " + orderDetailsDTO.getRecipientStreet() + " " + orderDetailsDTO.getRecipientHomeNumber());
        fromAddress.setText(orderDetailsDTO.getSenderCity() + " " + orderDetailsDTO.getSenderStreet() + " " + orderDetailsDTO.getSenderHomeNumber());
        recipientMobile.setText(orderDetailsDTO.getRecipientMobile());
        senderMobile.setText(orderDetailsDTO.getSenderMobile());
        price.setText(orderDetailsDTO.getPrice().toString());
        senderDetails.setText(orderDetailsDTO.getSenderName());
    }
}

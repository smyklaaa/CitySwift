package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;

public class OrderDetailsController {
    public void setObject(int orderId) {
        System.out.println(orderId);
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getOrderById", orderId, UserSession.getUserToken().getToken()));
    }
}

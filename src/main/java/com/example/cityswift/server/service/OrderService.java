package com.example.cityswift.server.service;

import com.example.cityswift.dto.CreateOrderRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.OrderRepository;
import com.example.cityswift.server.repository.PackageRepository;

import java.io.Serializable;
import java.util.List;

public class OrderService {
    private static final OrderRepository orderRepository = new OrderRepository();
    private static final PackageRepository packageRepository = new PackageRepository();
    private static final AddressRepository addressRepository = new AddressRepository();


    public static ServerResponse getReceivedUserOrders(int currentUserId){
        List<OrderModel> listOfOrders = orderRepository.fetchUserReceivedOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public static ServerResponse getSendUserOrders(int currentUserId){
        List<OrderModel> listOfOrders = orderRepository.fetchUserSendOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;

    }

    public static ServerResponse createOrder(CreateOrderRequest data, Integer privateToken) {
        // addressRepository.insertAddress();
        return null;
    }
}

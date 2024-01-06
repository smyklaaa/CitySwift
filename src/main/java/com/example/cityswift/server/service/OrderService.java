package com.example.cityswift.server.service;

import com.example.cityswift.dto.CreateOrderDTO;
import com.example.cityswift.dto.CreateOrderRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.model.RecipientModel;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.OrderRepository;
import com.example.cityswift.server.repository.PackageRepository;
import com.example.cityswift.server.repository.RecipientRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final PackageRepository packageRepository = new PackageRepository();
    private final AddressRepository addressRepository = new AddressRepository();
    private final RecipientRepository recipientRepository = new RecipientRepository();


    public ServerResponse getReceivedUserOrders(int currentUserId){
        List<OrderModel> listOfOrders = orderRepository.fetchUserReceivedOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse getSendUserOrders(int currentUserId){
        List<OrderModel> listOfOrders = orderRepository.fetchUserSendOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;

    }


    public ServerResponse createOrder(CreateOrderDTO data, Integer privateToken) {
        int recipient = recipientRepository.createRecipient(data.getRecipientMobile(), data.getRecipientMail());
        int packageId = packageRepository.createPackage(data.getCreatePackageDTO());
        int address = addressRepository.createAddress(data.getCreateAddressDTO());
            orderRepository.createOrder(privateToken, recipient, packageId, address);

        return null;
    }
}

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
    private static final OrderRepository orderRepository = new OrderRepository();
    private static final PackageRepository packageRepository = new PackageRepository();
    private static final AddressRepository addressRepository = new AddressRepository();
    private static final RecipientRepository recipientRepository = new RecipientRepository();


    public static ServerResponse getAllUserOrders(int currentUserId){
        List<OrderModel> listOfOrders = orderRepository.fetchUserOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;

    }


    public static ServerResponse createOrder(CreateOrderDTO data, Integer privateToken) {
        int recipient = recipientRepository.createRecipient(data.getRecipientMobile(), data.getRecipientMail());
        int packageId = packageRepository.createPackage(data.getCreatePackageDTO());
        int address = addressRepository.createAddress(data.getCreateAddressDTO());
            orderRepository.createOrder(privateToken, recipient, packageId, address);

        return null;
    }
}

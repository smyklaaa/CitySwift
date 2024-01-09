package com.example.cityswift.server.service;

import com.example.cityswift.dto.CreateOrderDTO;
import com.example.cityswift.dto.CreateOrderRequest;
import com.example.cityswift.dto.OrderDetailsDTO;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.Server;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.model.RecipientModel;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.*;
import com.example.cityswift.server.util.MailSender;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final PackageRepository packageRepository = new PackageRepository();
    private final AddressRepository addressRepository = new AddressRepository();
    private final RecipientRepository recipientRepository = new RecipientRepository();
    private final UserRepository userRepository = new UserRepository();


    public ServerResponse getReceivedUserOrders(int currentUserId) {
        List<OrderModel> listOfOrders = orderRepository.fetchUserReceivedOrderData(currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData((Serializable) listOfOrders);
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse getSendUserOrders(int currentUserId) {
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

    public ServerResponse getOrderList(int statusId) {
        List<OrderDetailsDTO> orderDetailsDTOS = orderRepository.fetchOrderList(statusId);
        return ServerResponseService.createPositiveServerResponse((Serializable) orderDetailsDTOS);
    }

    public ServerResponse getOrderById(int orderId) {
        Optional<OrderDetailsDTO> orderModel = orderRepository.fetchOrderById(orderId);
        return orderModel.map(ServerResponseService::createPositiveServerResponse).orElseGet(ServerResponseService::notFoundServerResponse);
    }

    public ServerResponse takePackage(String orderId, int userId) {
        orderRepository.takePackage(userId, Integer.parseInt(orderId));
        Optional<OrderModel> orderDetailsDTOOptional = orderRepository.getOrderById(Integer.parseInt(orderId));
        if (orderDetailsDTOOptional.isPresent()) {
            OrderModel orderDetailsDTO = orderDetailsDTOOptional.get();
            Optional<RecipientModel> recipientOptional = recipientRepository.fetchRecipientData(orderDetailsDTO.getRecipientId());
            Optional<UserModel> courierOptional = userRepository.getUserById(orderDetailsDTO.getCourierId());

            if(recipientOptional.isPresent() && courierOptional.isPresent()){
                RecipientModel recipient = recipientOptional.get();
                UserModel courier = courierOptional.get();
                MailSender mailSender = new MailSender();
                mailSender.sendMail(recipient.getMail(), "Zamówienie zostało odebrane", "Twoje zamówienie zostało odebrane przez kuriera: " + courier.getFirstName() + " " + courier.getLastName());
            }
        }
        return ServerResponseService.createPositiveServerResponse(null);
    }
}

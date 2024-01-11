package com.example.cityswift.server.util;


import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.UserRepository;
import com.example.cityswift.server.service.AddressService;
import com.example.cityswift.server.service.OrderService;
import com.example.cityswift.server.service.SettingsService;
import com.example.cityswift.server.service.UserService;

import java.util.List;
import java.util.Optional;

public class HandleClientAction {
    private final UserRepository userRepository = new UserRepository();
    private final UserService userService = new UserService();
    private final OrderService orderService = new OrderService();
    private final SettingsService settingsService = new SettingsService();
    private final AddressService addressService = new AddressService();

    public ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

        try {
            switch (clientRequest.getAction()) {
                case "getUserBasicData":
                    serverResponse = userService.getUserBasicData(
                            clientTokenToUserId((int) clientRequest.getData())
                    );
                    break;
                case "login":
                    serverResponse = userService.login((UserCredential) clientRequest.getData());
                    break;
                case "getUsersLivingInArea":
                    serverResponse = addressService.ifUsersInParticularArea((ParticularArea) clientRequest.getData());
                    break;
                case "getHomeViewData":
                    serverResponse = userService.getHomeViewData(clientTokenToUserId((int) clientRequest.getData()));
                    break;
                case "signup":
                    serverResponse = userService.signup((CreateUserData) clientRequest.getData());
                    break;
                case "searchUser":
                    serverResponse = userService.userSearch((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "addFriend":
                    serverResponse = userService.addFriend((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "acceptFriend":
                    serverResponse = userService.acceptFriend((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getFriends":
                    serverResponse = userService.getFriends((Boolean) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "createOrder":
                    serverResponse = orderService.createOrder((CreateOrderDTO) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getUserReceivedOrdersHistory":
                    serverResponse = orderService.getReceivedUserOrders(
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getUserSendOrdersHistory":
                    serverResponse = orderService.getSendUserOrders(
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changePassword":
                    serverResponse = settingsService.changePassword((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changeMobile":
                    serverResponse = settingsService.changeMobile((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changeAddress":
                    serverResponse = settingsService.changeAddress((AddressDTO) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getOrderList":
                    serverResponse = orderService.getOrderList((int) clientRequest.getData());
                    break;
                case "getOrderById":
                    serverResponse = orderService.getOrderById((int) clientRequest.getData());
                    break;
                case "takePackage":
                    serverResponse = orderService.takePackage((String) clientRequest.getData(), clientTokenToUserId(clientRequest.getPrivateToken()));
                    break;
                case "getCourierCurrentOrder":
                    serverResponse = orderService.getCourierCurrentOrder(clientTokenToUserId(clientRequest.getPrivateToken()));
                    break;
                case "cancelOrder":
                    serverResponse = orderService.cancelOrderDelivery((int) clientRequest.getData());
                    break;
                case "sendDeliveryNotification":
                    serverResponse = orderService.sendDeliveryNotification((int) clientRequest.getData());
                    break;
                case "endDelivery":
                    serverResponse = orderService.endDelivery((EndDeliveryData) clientRequest.getData());
                    break;
                default:
                    serverResponse.setResultCode(404);
                    serverResponse.setResultMessage("Action not found");
                    break;
            }
        } catch (Exception e) {
            serverResponse.setResultCode(500);
            serverResponse.setResultMessage("Internal server error");
        }

        return serverResponse;
    }

    public int clientTokenToUserId(Integer clientToken) {
        Optional<UserModel> userByPrivateToken =
                userRepository.getUserByPrivateToken(clientToken);
        return userByPrivateToken.map(UserModel::getId).orElse(-1);
    }
}

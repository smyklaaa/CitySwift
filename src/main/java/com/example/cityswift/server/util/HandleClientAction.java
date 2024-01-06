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
    private static final UserRepository userRepository = new UserRepository();

    public static ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

        try {
            switch (clientRequest.getAction()) {
                case "getUserBasicData":
                    serverResponse = UserService.getUserBasicData(
                            clientTokenToUserId((int) clientRequest.getData())
                    );
                    break;
                case "login":
                    serverResponse = UserService.login((UserCredential) clientRequest.getData());
                    break;
                case "getUsersLivingInArea":
                    serverResponse = AddressService.ifUsersInParticularArea((ParticularArea) clientRequest.getData());
                    break;
                case "signup":
                    serverResponse = UserService.signup((CreateUserData) clientRequest.getData());
                    break;
                case "searchUser":
                    serverResponse = UserService.userSearch((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "addFriend":
                    serverResponse = UserService.addFriend((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "acceptFriend":
                    serverResponse = UserService.acceptFriend((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getFriends":
                    serverResponse = UserService.getFriends((Boolean) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "createOrder":
                    serverResponse = OrderService.createOrder((CreateOrderDTO) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getUserReceivedOrdersHistory":
                    serverResponse = OrderService.getReceivedUserOrders(
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "getUserSendOrdersHistory":
                    serverResponse = OrderService.getSendUserOrders(
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changePassword":
                    serverResponse = SettingsService.changePassword((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changeMobile":
                    serverResponse = SettingsService.changeMobile((String) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
                    break;
                case "changeAddress":
                    serverResponse = SettingsService.changeAddress((AddressDTO) clientRequest.getData(),
                            clientTokenToUserId((int) clientRequest.getPrivateToken()));
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

    public static int clientTokenToUserId(Integer clientToken) {
        Optional<UserModel> userByPrivateToken = userRepository.getUserByPrivateToken(clientToken);
        return userByPrivateToken.map(UserModel::getId).orElse(-1);
    }
}

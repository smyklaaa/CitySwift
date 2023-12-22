package com.example.cityswift.server.util;


import com.example.cityswift.dto.*;
import com.example.cityswift.server.service.AddressService;
import com.example.cityswift.server.service.UserService;

public class HandleClientAction {
    public static ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

        try {
            switch (clientRequest.getAction()) {
                case "getUserBasicData":
                    serverResponse = UserService.getUserBasicData((int) clientRequest.getData());
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
                    serverResponse = UserService.userSearch((String) clientRequest.getData(), (Integer) clientRequest.getPrivateToken());
                    break;
                case "addFriend":
                    serverResponse = UserService.addFriend((String) clientRequest.getData(), (Integer) clientRequest.getPrivateToken());
                    break;
                case "acceptFriend":
                    serverResponse = UserService.acceptFriend((String) clientRequest.getData(), (Integer) clientRequest.getPrivateToken());
                    break;
                case "getFriends":
                    serverResponse = UserService.getFriends((Boolean) clientRequest.getData(), (Integer) clientRequest.getPrivateToken());
                    break;
                default:
                    serverResponse.setResultCode(404);
                    serverResponse.setResultMessage("Action not found");
                    break;
            }
        } catch (Exception e) {
            System.out.println("ASDSAASASDDASSDAASDDASD");
            serverResponse.setResultCode(500);
            serverResponse.setResultMessage("Internal server error");
        }

        return serverResponse;
    }
}

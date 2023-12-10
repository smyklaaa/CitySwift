package com.example.cityswift.server.util;


import com.example.cityswift.dto.*;
import com.example.cityswift.server.service.AddressService;
import com.example.cityswift.server.service.UserService;

public class HandleClientAction {
    public static ServerResponse handleClientAction(ClientRequest clientRequest) {
        ServerResponse serverResponse = new ServerResponse();

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
            case "register":
                serverResponse = UserService.register((CreateUserData) clientRequest.getData());
                break;
            default:
                serverResponse.setResultCode(404);
                serverResponse.setResultMessage("Action not found");
                break;
        }

        return serverResponse;
    }
}

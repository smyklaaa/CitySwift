package com.example.cityswift.server.service;

import com.example.cityswift.dto.AddressDTO;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.repository.UserRepository;

public class SettingsService {

    private final UserRepository userRepository = new UserRepository();

    public ServerResponse changePassword(String password, int currentUserId) {
        userRepository.changePassword(password, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse changeMobile(String mobile, int currentUserId) {
        userRepository.changeMobile(mobile, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse changeAddress(AddressDTO newAddress, int currentUserId) {
        userRepository.changeAddress(newAddress, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }
}

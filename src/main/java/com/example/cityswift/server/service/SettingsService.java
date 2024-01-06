package com.example.cityswift.server.service;

import com.example.cityswift.dto.AddressDTO;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.dto.UserCredential;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.repository.RecipientRepository;
import com.example.cityswift.server.repository.SettingsRepository;

import java.io.Serializable;
import java.util.List;

public class SettingsService {

    private final SettingsRepository settingsRepository = new SettingsRepository();

    public ServerResponse changePassword(String password, int currentUserId) {
        settingsRepository.changePassword(password, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse changeMobile(String mobile, int currentUserId) {
        settingsRepository.changeMobile(mobile, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }

    public ServerResponse changeAddress(AddressDTO newAddress, int currentUserId) {
        settingsRepository.changeAddress(newAddress, currentUserId);
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setResultCode(200);
        return serverResponse;
    }
}

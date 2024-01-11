package com.example.cityswift.server.service;

import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.dto.UserCredential;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.ForgotPasswordRepository;

import java.util.List;
import java.util.Optional;

public class ForgotPasswordService {
    private final ForgotPasswordRepository forgotPasswordRepository = new ForgotPasswordRepository();

    public ServerResponse changeIfUserRegistered(UserModel mail) {
        Optional<UserModel> userMail = forgotPasswordRepository.getUserMail(mail.getMail());
        ServerResponse serverResponse = new ServerResponse();

        if(!userMail.isEmpty()){
            serverResponse.setResultCode(200);
            return serverResponse;
        }else{
            serverResponse.setResultCode(404);
            return serverResponse;
        }
    }
}

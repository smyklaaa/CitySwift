package com.example.cityswift.server.service;



import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private static final UserRepository userRepository = new UserRepository();

    public static ServerResponse getUserBasicData(int userId) {
        BasicUserData basicUserData = userRepository.fetchBasicUserDataById(userId);
        return ServerResponseService.createPositiveServerResponse(basicUserData);
    }

    public static ServerResponse login(UserCredential userCredential){
        Optional<UserModel> givenCredentials = userRepository.fetchUserCredentials(userCredential.getMail(),
                userCredential.getPassword());
        if(givenCredentials.isPresent()){

            return ServerResponseService.createPositiveServerResponse(new UserToken(givenCredentials.get().getId()));


        }else{
            return ServerResponseService.notFoundServerResponse();
        }
    }

    public static ServerResponse register(CreateUserData createUserData){
        int userId = userRepository.insertUser(createUserData);
        if(userId > 0){
            return ServerResponseService.createPositiveServerResponse(new UserToken(userId));
        }else{
            return ServerResponseService.serverErrorResponse();
        }
    }
}
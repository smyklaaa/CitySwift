package com.example.cityswift.server.service;


import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.FriendsRepository;
import com.example.cityswift.server.repository.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserService {
    private static final UserRepository userRepository = new UserRepository();
    private static final FriendsRepository friendsRepository = new FriendsRepository();

    public static ServerResponse getUserBasicData(int userId) {
        BasicUserData basicUserData = userRepository.fetchBasicUserDataById(userId);
        return ServerResponseService.createPositiveServerResponse(basicUserData);
    }

    public static ServerResponse login(UserCredential userCredential) {
        Optional<UserModel> givenCredentials = userRepository.fetchUserCredentials(userCredential.getMail(),
                userCredential.getPassword());
        if (givenCredentials.isPresent()) {

            return ServerResponseService.createPositiveServerResponse(new UserToken(givenCredentials.get().getId()));


        } else {
            return ServerResponseService.notFoundServerResponse();
        }
    }

    public static ServerResponse signup(CreateUserData createUserData) {
        String privateToken = generateRandomToken();
        String publicToken = generateRandomToken();
        int isUserInserted = userRepository.insertUser(createUserData, privateToken, publicToken);
        Optional<UserModel> userByMail = userRepository.getUserByMail(createUserData.getMail());
        AddressService.signup(createUserData, userByMail.get().getId());
        if (isUserInserted > 0) {
            return ServerResponseService.createPositiveServerResponse(new UserToken(isUserInserted));
        } else {
            return ServerResponseService.userExistErrorResponse();
        }
    }

    public static ServerResponse userSearch(String userSearch, Integer privateToken) {
        List<UserModel> userModels = userRepository.fetchUserSearch(userSearch, privateToken);
        List<BasicUserData> result = new ArrayList<>();
        for (UserModel userModel : userModels) {
            AddressRepository addressRepository = new AddressRepository();
            List<AddressModel> addressModel = addressRepository.fetchAddressesByUserId(userModel.getId());
            result.add(new BasicUserData(userModel, addressModel));
        }
        return ServerResponseService.createPositiveServerResponse((Serializable) result);
    }

    private static String generateRandomToken() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }

    public static ServerResponse addFriend(String data, Integer privateToken) {
        Optional<UserModel> userModel = userRepository.getUserByPublicToken(data);
        if (userModel.isPresent()) {
            if (!friendsRepository.checkIfFriends(userModel.get().getId(), privateToken)) {
                int isAdded = friendsRepository.addFriend(userModel.get().getId(), privateToken);
                if (isAdded > 0) {
                    return ServerResponseService.createPositiveServerResponse(null);
                }
            }
        }

        return ServerResponseService.notFoundServerResponse();
    }

    public static ServerResponse acceptFriend(String data, Integer privateToken) {
        Optional<UserModel> userModel = userRepository.getUserByPublicToken(data);
        if (userModel.isPresent()) {
            int isAdded = friendsRepository.acceptFriend(userModel.get().getId(), privateToken);
            if (isAdded > 0) {
                return ServerResponseService.createPositiveServerResponse(null);
            }
        }

        return ServerResponseService.notFoundServerResponse();
    }

    public static ServerResponse getFriends(Boolean accepted, Integer privateToken) {
        List<UserModel> userModels = new ArrayList<>();
        if(accepted == false){
            userModels = userRepository.getFriends(true, privateToken);
        } else{
            userModels = userRepository.getAcceptedFriends(privateToken);
        }
        List<BasicUserData> result = new ArrayList<>();
        for (UserModel userModel : userModels) {
            AddressRepository addressRepository = new AddressRepository();
            List<AddressModel> addressModel = addressRepository.fetchAddressesByUserId(userModel.getId());
            result.add(new BasicUserData(userModel, addressModel));
        }
        return ServerResponseService.createPositiveServerResponse((Serializable) result);
    }
}

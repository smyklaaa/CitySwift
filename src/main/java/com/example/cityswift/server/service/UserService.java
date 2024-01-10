package com.example.cityswift.server.service;


import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.GlobalConfigModel;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.FriendsRepository;
import com.example.cityswift.server.repository.GlobalConfigRepository;
import com.example.cityswift.server.repository.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final FriendsRepository friendsRepository = new FriendsRepository();
    private final AddressService addressService = new AddressService();
    private final GlobalConfigRepository globalConfigRepository = new GlobalConfigRepository();


    public ServerResponse getUserBasicData(int userId) {
        BasicUserData basicUserData = userRepository.fetchBasicUserDataById(userId);
        return ServerResponseService.createPositiveServerResponse(basicUserData);
    }

    public ServerResponse login(UserCredential userCredential) {
        Optional<UserModel> givenCredentials = userRepository.fetchUserCredentials(userCredential.getMail(),
                userCredential.getPassword());
        if (givenCredentials.isPresent()) {

            return ServerResponseService.createPositiveServerResponse(new UserToken(givenCredentials.get().getPrivateToken()));


        } else {
            return ServerResponseService.notFoundServerResponse();
        }
    }

    public ServerResponse signup(CreateUserData createUserData) {
        String privateToken = generateRandomToken();
        String publicToken = generateRandomToken();
        int isUserInserted = userRepository.insertUser(createUserData, privateToken, publicToken);
        Optional<UserModel> userByMail = userRepository.getUserByMail(createUserData.getMail());
        addressService.signup(createUserData, userByMail.get().getId());
        if (isUserInserted > 0) {
            return ServerResponseService.createPositiveServerResponse(new UserToken(isUserInserted));
        } else {
            return ServerResponseService.userExistErrorResponse();
        }
    }

    public ServerResponse userSearch(String userSearch, Integer privateToken) {
        List<UserModel> userModels = userRepository.fetchUserSearch(userSearch, privateToken);
        List<BasicUserData> result = new ArrayList<>();
        for (UserModel userModel : userModels) {
            AddressRepository addressRepository = new AddressRepository();
            List<AddressModel> addressModel = addressRepository.fetchAddressesByUserId(userModel.getId());
            result.add(new BasicUserData(userModel, addressModel));
        }
        return ServerResponseService.createPositiveServerResponse((Serializable) result);
    }

    private String generateRandomToken() {
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }

    public ServerResponse addFriend(String publicToken, Integer privateToken) {
        return userRepository.getUserByPublicToken(publicToken)
                .filter(user -> !friendsRepository.checkIfFriends(user.getId(), privateToken))
                .map(user -> addFriendshipAndRespond(user, privateToken))
                .orElseGet(ServerResponseService::notFoundServerResponse);
    }

    private ServerResponse addFriendshipAndRespond(UserModel user, Integer privateToken) {
        int isAdded = friendsRepository.addFriend(user.getId(), privateToken);
        if (isAdded > 0) {
            return ServerResponseService.createPositiveServerResponse(null);
        } else {
            return ServerResponseService.notFoundServerResponse();
        }
    }

    public ServerResponse acceptFriend(String data, Integer privateToken) {
        Optional<UserModel> userModel = userRepository.getUserByPublicToken(data);
        if (userModel.isPresent()) {
            int isAdded = friendsRepository.acceptFriend(userModel.get().getId(), privateToken);
            if (isAdded > 0) {
                return ServerResponseService.createPositiveServerResponse(null);
            }
        }

        return ServerResponseService.notFoundServerResponse();
    }

    public ServerResponse getFriends(Boolean accepted, Integer privateToken) {
        List<UserModel> userModels = new ArrayList<>();
        if(accepted == false){
            userModels = userRepository.getFriends(false, privateToken);
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

    public ServerResponse getHomeViewData(int userId){
        HomeViewDetailsDTO homeViewDetailsDTO = new HomeViewDetailsDTO();
        BasicUserData basicUserData = userRepository.fetchBasicUserDataById(userId);
        homeViewDetailsDTO.setBasicUserData(basicUserData);
        Optional<GlobalConfigModel> globalConfig = globalConfigRepository.getGlobalConfig(globalConfigRepository.PRICE_PER_KILOMETER_PLN);
        homeViewDetailsDTO.setPricePerKm(globalConfig.get().getConfigValue());
        homeViewDetailsDTO.setTotalNumberOfOrders(userRepository.fetchTotalNumberOfOrders(userId));
        homeViewDetailsDTO.setYourNumberOfOrders(userRepository.fetchYourNumberOfOrders(userId));
        return ServerResponseService.createPositiveServerResponse(homeViewDetailsDTO);
    }

}

package com.example.cityswift.server.service;



import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.repository.AddressRepository;

import java.util.List;

public class AddressService {
    private static final AddressRepository  addressRepository = new AddressRepository();

    public static ServerResponse ifUsersInParticularArea(ParticularArea userArea){
        List<AddressModel> givenUserArea = addressRepository.fetchUsersInAddressArea(userArea);
        if(!givenUserArea.isEmpty()){
            return ServerResponseService.createPositiveServerResponse(new ListOfAddressInArea(givenUserArea));
        }else{
            return ServerResponseService.notFoundServerResponse();
        }
    }

    public static ServerResponse signup(CreateUserData createUserData, Integer userId){
        int insertUserAddress = addressRepository.insertAddress(createUserData, userId);
        if(insertUserAddress > 0){
            return ServerResponseService.createPositiveServerResponse(new UserToken(userId));
        }else{
            return ServerResponseService.userExistErrorResponse();
        }
    }
}

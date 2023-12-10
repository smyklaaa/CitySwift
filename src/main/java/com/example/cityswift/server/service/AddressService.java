package com.example.cityswift.server.service;



import com.example.cityswift.dto.ListOfAddressInArea;
import com.example.cityswift.dto.ParticularArea;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.repository.AddressRepository;

import java.util.List;

public class AddressService {
    private static final AddressRepository addressRepository = new AddressRepository();

    public static ServerResponse ifUsersInParticularArea(ParticularArea userArea){
        List<AddressModel> givenUserArea = addressRepository.fetchUsersInAddressArea(userArea);
        if(!givenUserArea.isEmpty()){

            return ServerResponseService.createPositiveServerResponse(new ListOfAddressInArea(givenUserArea));


        }else{
            return ServerResponseService.notFoundServerResponse();
        }
    }
}

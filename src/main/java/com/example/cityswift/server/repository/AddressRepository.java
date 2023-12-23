package com.example.cityswift.server.repository;


import com.example.cityswift.dto.CreateUserData;
import com.example.cityswift.dto.ParticularArea;
import com.example.cityswift.server.mapper.ToAddressModelMapper;
import com.example.cityswift.server.model.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {
    GenericRepository<AddressModel> repository = new GenericRepository<>();
    ToAddressModelMapper mapper = new ToAddressModelMapper();

    public List<AddressModel> fetchAddressesByUserId(Integer userId) {
        String sql = "SELECT * FROM ADDRESS WHERE user_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public List<AddressModel> fetchUsersInAddressArea(ParticularArea userArea) {
        String sql = "SELECT * FROM ADDRESS WHERE postal_code = ?";
        List<Object> params = new ArrayList<>();
        params.add(userArea.getArea());
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public int insertAddress(CreateUserData createUserData, Integer userId) {
        String sql = "INSERT INTO address (street, postal_code, home_number, door_key, is_main, user_id,city) " +
                "VALUES (?,?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(createUserData.getStreet());
        params.add(createUserData.getPostalCode());
        params.add(createUserData.getHomeNr());
        params.add(createUserData.getDoorKeyNr());
        params.add(true);
        params.add(userId);
        params.add(createUserData.getCity());
        return repository.insert(sql, params);
    }
}

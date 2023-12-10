package com.example.cityswift.server.repository;



import com.example.cityswift.dto.ParticularArea;
import com.example.cityswift.server.mapper.ToAddressModelMapper;
import com.example.cityswift.server.model.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {
    GenericRepository<AddressModel> repository = new GenericRepository<>();
    ToAddressModelMapper mapper = new ToAddressModelMapper();

    public List<AddressModel> fetchAddressesByUserId(int userId) {
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
}

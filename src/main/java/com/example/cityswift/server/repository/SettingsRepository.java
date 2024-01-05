package com.example.cityswift.server.repository;

import com.example.cityswift.dto.AddressDTO;
import com.example.cityswift.server.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();

    public void changePassword(String password, Integer privateToken) {
        String sql = "UPDATE app_user SET password = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(password);
        params.add(privateToken);

        repository.insert(sql, params);
    }

    public void changeMobile(String mobile, Integer privateToken) {
        String sql = "UPDATE app_user SET mobile = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(mobile);
        params.add(privateToken);

        repository.insert(sql, params);
    }

    public void changeAddress(AddressDTO newAddress, Integer privateToken) {
        String sql = "UPDATE address SET city = ?, street = ?, postal_code = ?, home_number = ?, door_key = ?  WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(newAddress.getCity());
        params.add(newAddress.getStreetName());
        params.add(newAddress.getPostalCode());
        params.add(newAddress.getHomeNr());
        params.add(newAddress.getDoorKey());
        params.add(privateToken);

        repository.insert(sql, params);
    }
}

package com.example.cityswift.server.repository;

import com.example.cityswift.dto.OrderData;
import com.example.cityswift.dto.ParticularArea;
import com.example.cityswift.server.mapper.ToAddressModelMapper;
import com.example.cityswift.server.mapper.ToOrderModelMapper;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();
    ToOrderModelMapper mapper = new ToOrderModelMapper();


    public List<OrderModel> fetchUserOrderData(int currentUserId) {
        String sql = "SELECT orders.*, app_user.first_name, app_user.last_name" +
                    " FROM orders" +
                    " JOIN app_user ON app_user.id = orders.sender_id" +
                    " WHERE orders.recipient_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }
}
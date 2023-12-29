package com.example.cityswift.server.repository;

import com.example.cityswift.dto.CreateOrderDTO;
import com.example.cityswift.server.mapper.ToOrderModelMapper;
import com.example.cityswift.server.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();
    ToOrderModelMapper mapper = new ToOrderModelMapper();


    public List<OrderModel> fetchUserOrderData(int currentUserId) {
        String sql = "SELECT * FROM orders WHERE sender_id = ? OR recipient_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public void createOrder(Integer privateToken, int id, int packageId, int address) {
        String sql = "INSERT INTO orders (sender_id, recipient_id, package_id, address_id, status_id, price) VALUES (?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(id);
        params.add(packageId);
        params.add(address);
        params.add(1);
        params.add(1);

        repository.insert(sql, params);
    }
}
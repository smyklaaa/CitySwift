package com.example.cityswift.server.repository;

import com.example.cityswift.server.mapper.ToOrderModelMapper;
import com.example.cityswift.server.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();
    ToOrderModelMapper mapper = new ToOrderModelMapper();


    public List<OrderModel> fetchUserReceivedOrderData(int currentUserId) {
        String sql = "SELECT orders.*, app_user.first_name, app_user.last_name" +
                    " FROM orders" +
                    " JOIN app_user ON app_user.id = orders.sender_id" +
                    " WHERE orders.recipient_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }

    public List<OrderModel> fetchUserSendOrderData(int currentUserId) {
        String sql = "SELECT orders.*, COALESCE(app_user.first_name,'Brak Danych'),COALESCE( app_user.last_name,'Brak Danych')," +
                "COALESCE( recipient.mail,'Brak Danych')" +
                " FROM orders" +
                " JOIN app_user ON app_user.id = orders.sender_id" +
                " JOIN recipient ON recipient.id = orders.sender_id"+
                " WHERE orders.sender_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, mapper, params);
    }
}
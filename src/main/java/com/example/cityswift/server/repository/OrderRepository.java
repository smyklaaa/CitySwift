package com.example.cityswift.server.repository;

import com.example.cityswift.server.mapper.ToReceivedPackagesMapper;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.mapper.ToSendPackagesMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();
    ToReceivedPackagesMapper toReceivedPackagesMapper = new ToReceivedPackagesMapper();
    ToSendPackagesMapper toSendPackagesMapper = new ToSendPackagesMapper();


    public List<OrderModel> fetchUserReceivedOrderData(int currentUserId) {
        String sql = "SELECT orders.*, app_user.first_name, app_user.last_name" +
                    " FROM orders" +
                    " JOIN app_user ON app_user.id = orders.sender_id" +
                    " JOIN recipient ON recipient.id = orders.recipient_id" +
                    " WHERE recipient.mail = (Select app_user.mail FROM app_user WHERE app_user.id = ?)";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, toReceivedPackagesMapper, params);
    }

    public List<OrderModel> fetchUserSendOrderData(int currentUserId) {
        String sql = "SELECT orders.*," +
                "COALESCE( recipient.mail,'Brak Danych') as mail"  +
                " FROM orders" +
                " JOIN recipient ON recipient.id = orders.recipient_id"+
                " LEFT JOIN app_user ON  app_user.mail = recipient.mail "+
                " WHERE orders.sender_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, toSendPackagesMapper, params);
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
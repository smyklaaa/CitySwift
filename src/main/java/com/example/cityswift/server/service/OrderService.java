package com.example.cityswift.server.service;

import com.example.cityswift.dto.BasicUserData;
import com.example.cityswift.dto.OrderData;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.OrderRepository;
import com.example.cityswift.server.repository.UserRepository;

import java.util.Optional;

public class OrderService {
    private static final AddressRepository addressRepository = new AddressRepository();
    private static final OrderRepository orderRepository = new OrderRepository();


    public static  void getAllUserOrders(){

    }
        Optional<OrderData> orderData = orderRepository.fetchUserOrderData(userId);

}
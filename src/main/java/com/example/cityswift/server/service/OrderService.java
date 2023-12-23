package com.example.cityswift.server.service;

import com.example.cityswift.dto.CreateOrderRequest;
import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.repository.AddressRepository;
import com.example.cityswift.server.repository.OrderRepository;
import com.example.cityswift.server.repository.PackageRepository;

public class OrderService {
    private static final OrderRepository orderRepository = new OrderRepository();
    private static final PackageRepository packageRepository = new PackageRepository();
    private static final AddressRepository addressRepository = new AddressRepository();

    public static ServerResponse createOrder(CreateOrderRequest data, Integer privateToken) {
        addressRepository.insertAddress(data.getAddressModel())
    }
}

package com.example.cityswift.server.repository;

import com.example.cityswift.dto.OrderDetailsDTO;
import com.example.cityswift.server.mapper.ToOrderDetailsDTOMapper;
import com.example.cityswift.server.mapper.ToReceivedPackagesMapper;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.GlobalConfigModel;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.mapper.ToSendPackagesMapper;
import com.example.cityswift.server.model.UserModel;
import com.example.cityswift.server.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    GenericRepository<OrderModel> repository = new GenericRepository<>();
    GenericRepository<OrderDetailsDTO> orderDetailsDTOGenericRepository = new GenericRepository<>();
    ToReceivedPackagesMapper toReceivedPackagesMapper = new ToReceivedPackagesMapper();
    ToSendPackagesMapper toSendPackagesMapper = new ToSendPackagesMapper();
    ToOrderDetailsDTOMapper toOrderDetailsDTOMapper = new ToOrderDetailsDTOMapper();
    GlobalConfigRepository globalConfigRepository = new GlobalConfigRepository();
    UserRepository userRepository = new UserRepository();
    AddressRepository addressRepository = new AddressRepository();

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
                "COALESCE( recipient.mail,'Brak Danych') as mail" +
                " FROM orders" +
                " JOIN recipient ON recipient.id = orders.recipient_id" +
                " LEFT JOIN app_user ON  app_user.mail = recipient.mail " +
                " WHERE orders.sender_id = ?";
        List<Object> params = new ArrayList<>();
        params.add(currentUserId);
        return repository.fetchMultipleRow(sql, toSendPackagesMapper, params);
    }

    public void createOrder(Integer privateToken, int id, int packageId, int address) {
        String sql = "INSERT INTO orders (sender_id, recipient_id, package_id, address_id, status_id, price, order_code) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Optional<GlobalConfigModel> currentPrice = globalConfigRepository.getGlobalConfig(globalConfigRepository.PRICE_PER_KILOMETER_PLN);
        if (currentPrice.isEmpty()) {
            throw new RuntimeException("Nie znaleziono ceny za kilometr");
        }

        double price = createPrice(privateToken, String.valueOf(address));

        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(id);
        params.add(packageId);
        params.add(address);
        params.add(1);
        params.add(price);
        params.add(createCode());

        repository.insert(sql, params);
    }

    private double createPrice(int senderId, String recipientAddressId) {
        Optional<AddressModel> senderAddress = addressRepository.fetchAddressByUserId(senderId);
        Optional<AddressModel> recipientAddress = addressRepository.fetchAddressById(Integer.valueOf(recipientAddressId));

        if (senderAddress.isEmpty() || recipientAddress.isEmpty()) {
            throw new RuntimeException("Nie znaleziono adresu");
        }
        return calculatePrice(senderAddress.get(), recipientAddress.get());
    }

    private double calculatePrice(AddressModel senderAddress, AddressModel recipientAddress) {
        Optional<GlobalConfigModel> currentPrice = globalConfigRepository.getGlobalConfig(globalConfigRepository.PRICE_PER_KILOMETER_PLN);
        if (currentPrice.isEmpty()) {
            throw new RuntimeException("Nie znaleziono ceny za kilometr");
        }

        String senderAddressString = senderAddress.getCity() + "+" + senderAddress.getStreet() + "+" + senderAddress.getHomeNumber();
        String recipientAddressString = recipientAddress.getCity() + "+" + recipientAddress.getStreet() + "+" + recipientAddress.getHomeNumber();

        senderAddressString = senderAddressString.replace(' ', '_');
        recipientAddressString = recipientAddressString.replace(' ', '_');

        String[] senderCoords = DistanceCalculator.geocodeAddress(senderAddressString);
        String[] recipientCoords = DistanceCalculator.geocodeAddress(recipientAddressString);

        double v = DistanceCalculator.calculateDistance(senderCoords, recipientCoords);
        return v * Double.parseDouble(currentPrice.get().getConfigValue());
    }

    public List<OrderDetailsDTO> fetchOrderList(int statusId) {
        String sql =
                "SELECT orders.message, orders.price, orders.id as order_id, " +
                        "       package.height as package_height, package.width as package_width, package.depth as package_depth, package.weight as package_weight, " +
                        "       recipient.mail as recipient_mail, recipient.mobile as recipient_mobile, " +
                        "       recipient_address.street as recipient_street, recipient_address.city as recipient_city, recipient_address.home_number as recipient_home_number, " +
                        "       sender_address.street as sender_street, sender_address.city as sender_city, sender_address.home_number as sender_home_number, " +
                        "       app_user.mobile as sender_mobile, " +
                        "       concat(app_user.first_name, ' ', app_user.last_name) as sender_name " +
                        "       FROM orders " +
                        "       INNER JOIN app_user ON orders.sender_id = app_user.id " +
                        "       INNER JOIN recipient ON orders.recipient_id = recipient.id " +
                        "       INNER JOIN address as recipient_address ON orders.address_id = recipient_address.id " +
                        "       INNER JOIN address as sender_address ON app_user.id = sender_address.user_id " +
                        "       INNER JOIN package ON orders.package_id = package.id " +
                        "       WHERE status_id = ?;";

        List<Object> params = new ArrayList<>();
        params.add(statusId);
        return orderDetailsDTOGenericRepository.fetchMultipleRow(sql, toOrderDetailsDTOMapper, params);
    }

    public Optional<OrderDetailsDTO> fetchOrderById(int orderId) {
        String sql =
                "SELECT orders.message, orders.price, orders.id as order_id, " +
                        "       package.height as package_height, package.width as package_width, package.depth as package_depth, package.weight as package_weight, " +
                        "       recipient.mail as recipient_mail, recipient.mobile as recipient_mobile, " +
                        "       recipient_address.street as recipient_street, recipient_address.city as recipient_city, recipient_address.home_number as recipient_home_number, " +
                        "       sender_address.street as sender_street, sender_address.city as sender_city, sender_address.home_number as sender_home_number, " +
                        "       app_user.mobile as sender_mobile, " +
                        "       concat(app_user.first_name, ' ', app_user.last_name) as sender_name " +
                        "       FROM orders " +
                        "       INNER JOIN app_user ON orders.sender_id = app_user.id " +
                        "       INNER JOIN recipient ON orders.recipient_id = recipient.id " +
                        "       INNER JOIN address as recipient_address ON orders.address_id = recipient_address.id " +
                        "       INNER JOIN address as sender_address ON app_user.id = sender_address.user_id " +
                        "       INNER JOIN package ON orders.package_id = package.id " +
                        "       WHERE orders.id = ?;";

        List<Object> params = new ArrayList<>();
        params.add(orderId);
        return orderDetailsDTOGenericRepository.fetchSingleRow(sql, toOrderDetailsDTOMapper, params);
    }

    public Optional<OrderModel> getOrderById(int orderId) {
        String sql = "SELECT orders.*, app_user.first_name, app_user.last_name" +
                " FROM orders" +
                " JOIN app_user ON app_user.id = orders.sender_id" +
                " JOIN recipient ON recipient.id = orders.recipient_id" +
                " AND orders.id = ?";
        List<Object> params = new ArrayList<>();
        params.add(orderId);
        return repository.fetchSingleRow(sql, toReceivedPackagesMapper, params);
    }

    public void takePackage(int userId, int orderId) {
        String sql = "UPDATE orders SET status_id = 2, courier_id = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(orderId);
        repository.update(sql, params);
    }

    public int createCode() {
        return (int) (Math.random() * 1000000);
    }

    public Optional<OrderDetailsDTO> getCourierCurrentOrder(int courierId) {
        String sql =
                "SELECT orders.message, orders.price, orders.id as order_id, " +
                        "       package.height as package_height, package.width as package_width, package.depth as package_depth, package.weight as package_weight, " +
                        "       recipient.mail as recipient_mail, recipient.mobile as recipient_mobile, " +
                        "       recipient_address.street as recipient_street, recipient_address.city as recipient_city, recipient_address.home_number as recipient_home_number, " +
                        "       sender_address.street as sender_street, sender_address.city as sender_city, sender_address.home_number as sender_home_number, " +
                        "       app_user.mobile as sender_mobile, " +
                        "       concat(app_user.first_name, ' ', app_user.last_name) as sender_name " +
                        "       FROM orders " +
                        "       INNER JOIN app_user ON orders.sender_id = app_user.id " +
                        "       INNER JOIN recipient ON orders.recipient_id = recipient.id " +
                        "       INNER JOIN address as recipient_address ON orders.address_id = recipient_address.id " +
                        "       INNER JOIN address as sender_address ON app_user.id = sender_address.user_id " +
                        "       INNER JOIN package ON orders.package_id = package.id " +
                        "       WHERE orders.courier_id = ? AND orders.status_id = 2;";

        List<Object> params = new ArrayList<>();
        params.add(courierId);
        return orderDetailsDTOGenericRepository.fetchSingleRow(sql, toOrderDetailsDTOMapper, params);
    }

    public void cancelOrderDelivery(int orderId) {
        String sql = "UPDATE orders SET status_id = 1 WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(orderId);
        repository.update(sql, params);
    }

    public void endDelivery(int orderId) {
        String sql = "UPDATE orders SET status_id = 3 WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(orderId);
        repository.update(sql, params);
    }
}
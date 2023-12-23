package com.example.cityswift.server.mapper;





import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.OrderModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToOrderModelMapper implements RowMapper<OrderModel> {

    @Override
    public OrderModel mapRow(ResultSet rs) throws SQLException {
        return new OrderModel(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("courier_id"),
                rs.getInt("price"),
                rs.getInt("package_id"),
                rs.getInt("status_id"),
                rs.getInt("recipient_id"),
                rs.getString("message")
        );    }
}

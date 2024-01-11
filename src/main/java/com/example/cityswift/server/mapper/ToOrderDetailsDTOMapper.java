package com.example.cityswift.server.mapper;

import com.example.cityswift.dto.OrderDetailsDTO;
import com.example.cityswift.server.interfaces.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToOrderDetailsDTOMapper implements RowMapper<OrderDetailsDTO> {
    @Override
    public OrderDetailsDTO mapRow(ResultSet rs) throws SQLException {
        OrderDetailsDTO orderDetails = new OrderDetailsDTO();
        orderDetails.setOrderId(rs.getString("order_id"));
        orderDetails.setMessage(rs.getString("message"));
        orderDetails.setPrice(rs.getDouble("price"));

        orderDetails.setPackageHeight(rs.getDouble("package_height"));
        orderDetails.setPackageWidth(rs.getDouble("package_width"));
        orderDetails.setPackageDepth(rs.getDouble("package_depth"));
        orderDetails.setPackageWeight(rs.getDouble("package_weight"));

        orderDetails.setRecipientMail(rs.getString("recipient_mail"));
        orderDetails.setRecipientMobile(rs.getString("recipient_mobile"));

        orderDetails.setRecipientStreet(rs.getString("recipient_street"));
        orderDetails.setRecipientCity(rs.getString("recipient_city"));
        orderDetails.setRecipientHomeNumber(rs.getString("recipient_home_number"));

        orderDetails.setSenderStreet(rs.getString("sender_street"));
        orderDetails.setSenderCity(rs.getString("sender_city"));
        orderDetails.setSenderHomeNumber(rs.getString("sender_home_number"));

        orderDetails.setSenderName(rs.getString("sender_name"));
        orderDetails.setSenderMobile(rs.getString("sender_mobile"));


        return orderDetails;
    }
}

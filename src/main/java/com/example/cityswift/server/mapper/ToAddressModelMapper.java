package com.example.cityswift.server.mapper;





import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.AddressModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToAddressModelMapper implements RowMapper<AddressModel> {

    @Override
    public AddressModel mapRow(ResultSet rs) throws SQLException {
        return new AddressModel(
                rs.getInt("id"),
                rs.getString("street"),
                rs.getString("postal_code"),
                rs.getString("home_number"),
                rs.getString("door_key"),
                rs.getBoolean("is_main")
        );    }
}

package com.example.cityswift.server.mapper;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.FriendshipModel;
import com.example.cityswift.server.model.GlobalConfigModel;
import com.example.cityswift.server.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToMailModelMapper implements RowMapper<UserModel> {


    @Override
    public UserModel mapRow(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getString("mail")
        );
    }
}

package com.example.cityswift.server.mapper;

import com.example.cityswift.server.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToMailModelMapper {

    public UserModel UserModel(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getString("mail")
        );    }
}

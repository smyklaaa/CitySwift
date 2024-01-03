package com.example.cityswift.server.mapper;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.FriendshipModel;
import com.example.cityswift.server.model.RecipientModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToRecipientModelMapper implements RowMapper<RecipientModel> {


    @Override
    public RecipientModel mapRow(ResultSet rs) throws SQLException {
        return new RecipientModel(
                rs.getString("mobile"),
                rs.getString("email")
        );
    }
}

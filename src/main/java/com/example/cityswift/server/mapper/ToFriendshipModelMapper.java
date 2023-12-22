package com.example.cityswift.server.mapper;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.FriendshipModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToFriendshipModelMapper implements RowMapper<FriendshipModel> {

    @Override
    public FriendshipModel mapRow(ResultSet rs) throws SQLException {
        return new FriendshipModel(
                rs.getInt("user_id"),
                rs.getInt("friend_id"),
                rs.getDate("friendship_date"),
                rs.getBoolean("accepted")
        );    }
}

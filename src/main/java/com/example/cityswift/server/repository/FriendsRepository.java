package com.example.cityswift.server.repository;

import com.example.cityswift.server.mapper.ToFriendshipModelMapper;
import com.example.cityswift.server.model.FriendshipModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FriendsRepository {
    GenericRepository<FriendshipModel> repository = new GenericRepository<>();
    ToFriendshipModelMapper mapper = new ToFriendshipModelMapper();

    public int addFriend(int id, Integer privateToken) {
        String sql = "INSERT INTO friendship (app_user_id_1, app_user_id_2, friendship_date, accepted)" +
                " VALUES (?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(id);
        java.util.Date now = new java.util.Date();
        params.add(new Date(now.getTime()));
        params.add(false);
        return repository.insert(sql, params);
    }

        public boolean checkIfFriends(int id, Integer privateToken) {
        String sql = "SELECT * FROM friendship WHERE app_user_id_1 = ? AND app_user_id_2 = ?";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(id);
        List<FriendshipModel> friendshipModels = repository.fetchMultipleRow(sql, mapper, params);
        return friendshipModels.size() > 0;
    }

    public int acceptFriend(int id, Integer privateToken) {
        String sql = "UPDATE friendship SET accepted = true WHERE app_user_id_2 = ? AND app_user_id_1 = ?";
        List<Object> params = new ArrayList<>();
        params.add(privateToken);
        params.add(id);
        return repository.update(sql, params);
    }
}

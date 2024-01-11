package com.example.cityswift.server.repository;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.mapper.ToMailModelMapper;
import com.example.cityswift.server.mapper.ToUserModelMapper;
import com.example.cityswift.server.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ForgotPasswordRepository {

    GenericRepository<UserModel> repository = new GenericRepository<>();
    ToMailModelMapper mapper = new ToMailModelMapper();

    public Optional<UserModel> getUserMail(String mail) {
        String sql = "SELECT app_user.mail FROM app_user WHERE mail = ? ";
        List<Object> params = new ArrayList<>();
        params.add(mail);
        return repository.fetchSingleRow(sql, (RowMapper<UserModel>) mapper, params);
    }
}

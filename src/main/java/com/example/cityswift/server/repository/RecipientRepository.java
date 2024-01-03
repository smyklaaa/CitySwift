package com.example.cityswift.server.repository;

import com.example.cityswift.server.mapper.ToRecipientModelMapper;
import com.example.cityswift.server.model.RecipientModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipientRepository {
    GenericRepository<RecipientModel> repository = new GenericRepository<>();
    ToRecipientModelMapper mapper = new ToRecipientModelMapper();


    public int createRecipient(String mobile, String mail) {
        String sql = "INSERT INTO recipient (mobile, email) VALUES (?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(mobile);
        params.add(mail);
        return repository.insert(sql, params);
    }

    public Optional<RecipientModel> fetchRecipientData(String mobile, String mail) {
        String sql = "SELECT * FROM recipient WHERE mobile = ? AND email = ?";
        List<Object> params = new ArrayList<>();
        params.add(mobile);
        params.add(mail);
        return repository.fetchSingleRow(sql, mapper, params);
    }
}

package com.example.cityswift.server.mapper;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.PackageModel;
import com.example.cityswift.server.model.RecipientModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToPackageModelMapper implements RowMapper<PackageModel> {

    @Override
    public PackageModel mapRow(ResultSet rs) throws SQLException {
        return new PackageModel(
                rs.getBigDecimal("height"),
                rs.getBigDecimal("width"),
                rs.getBigDecimal("length"),
                rs.getBigDecimal("weight")
        );
    }
}

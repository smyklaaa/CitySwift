package com.example.cityswift.server.mapper;

import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.model.GlobalConfigModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToGlobalConfigMapper implements RowMapper<GlobalConfigModel> {
    @Override
    public GlobalConfigModel mapRow(ResultSet rs) throws SQLException {
        GlobalConfigModel globalConfigModel = new GlobalConfigModel();
        globalConfigModel.setId(rs.getInt("id"));
        globalConfigModel.setConfigName(rs.getString("config_name"));
        globalConfigModel.setConfigValue(rs.getString("config_value"));
        return globalConfigModel;
    }
}

package com.example.cityswift.server.repository;

import com.example.cityswift.server.mapper.ToGlobalConfigMapper;
import com.example.cityswift.server.model.GlobalConfigModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GlobalConfigRepository {
    ToGlobalConfigMapper toGlobalConfigMapper = new ToGlobalConfigMapper();
    GenericRepository<GlobalConfigModel> globalConfigModelGenericRepository = new GenericRepository<>();

    public final String PRICE_PER_KILOMETER_PLN = "PRICE_PER_KILOMETER_PLN";

    public Optional<GlobalConfigModel> getGlobalConfig(String configName) {
        String sql = "SELECT * FROM global_config WHERE config_name = ?";
        List<Object> params = new ArrayList<>();
        params.add(configName);
        return globalConfigModelGenericRepository.fetchSingleRow(sql, toGlobalConfigMapper, params);
    }

}

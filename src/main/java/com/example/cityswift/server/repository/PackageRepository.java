package com.example.cityswift.server.repository;

import com.example.cityswift.dto.CreatePackageDTO;
import com.example.cityswift.server.mapper.ToPackageModelMapper;
import com.example.cityswift.server.model.PackageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PackageRepository {
    GenericRepository<PackageModel> repository = new GenericRepository<>();
    ToPackageModelMapper mapper = new ToPackageModelMapper();


    public int createPackage(CreatePackageDTO createPackageDTO) {
        String sql = "INSERT INTO package (height, width, depth, weight) VALUES (?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(createPackageDTO.getHeight());
        params.add(createPackageDTO.getWidth());
        params.add(createPackageDTO.getDepth());
        params.add(createPackageDTO.getWeight());

        return repository.insert(sql, params);
    }

}

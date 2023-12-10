package com.example.cityswift.server.repository;


import com.example.cityswift.server.Server;
import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.util.AppLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T> {

    public List<T> fetchMultipleRow(String sql, RowMapper<T> rowMapper, List<Object> params) {
        AppLogger.info("Executing query statement: " + sql);
        List<T> results = new ArrayList<>();
        Connection connection = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            PreparedStatement stmt = prepareStatement(connection, sql, params);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }

            AppLogger.info("Query executed, number of results found: " + results.size());
            return results;

        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.fetchMultipleData", e);
            throw new RuntimeException("Error fetching data", e);
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }


    public Optional<T> fetchSingleRow(String sql, RowMapper<T> rowMapper, List<Object> params) {
        AppLogger.info("Executing query: " + sql);
        Connection connection = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            PreparedStatement stmt = prepareStatement(connection, sql, params);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                AppLogger.info("Query executed, result found");
                return Optional.of(rowMapper.mapRow(resultSet));
            } else {
                AppLogger.info("Query executed, no result found");
                return Optional.empty();
            }

        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.fetchMultipleData", e);
            throw new RuntimeException("Error fetching data", e);
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }

    public int insert(String sql, List<Object> params) {
        AppLogger.info("Executing insert statement: " + sql);
        Connection connection = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            PreparedStatement stmt = prepareStatement(connection, sql, params);
            int affectedRows = stmt.executeUpdate();

            AppLogger.info("Insert executed, number of affected rows: " + affectedRows);
            return affectedRows;

        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.insert", e);
            throw new RuntimeException("Error executing insert", e);
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }


    private PreparedStatement prepareStatement(Connection connection, String sql, List<Object> params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt;
    }
}

package com.example.cityswift.server.repository;


import com.example.cityswift.server.Server;
import com.example.cityswift.server.exceptions.SimpleException;
import com.example.cityswift.server.interfaces.RowMapper;
import com.example.cityswift.server.util.AppLogger;

import java.sql.*;
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
            throw new SimpleException(500, "Error fetching data");
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
            throw new SimpleException(500, "Error fetching data");
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }

    public int insert(String sql, List<Object> params) {
        AppLogger.info("Executing insert statement: " + sql);
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            stmt = prepareStatement(connection, sql, params, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SimpleException(500, "Creating user failed, no rows affected.");
            }
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.insert", e);
            throw new SimpleException(500, "Error executing insert");
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
            Server.getConnectionPool().returnConnection(connection);
        }
    }


    private PreparedStatement prepareStatement(Connection connection, String sql, List<Object> params, int flags) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql, flags);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt;
    }


    private PreparedStatement prepareStatement(Connection connection, String sql, List<Object> params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt;
    }

    public int update(String sql, List<Object> params) {
        AppLogger.info("Executing update statement: " + sql);
        Connection connection = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            PreparedStatement stmt = prepareStatement(connection, sql, params);
            int affectedRows = stmt.executeUpdate();

            AppLogger.info("Update executed, number of affected rows: " + affectedRows);
            return affectedRows;

        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.update", e);
            throw new SimpleException(500, "Error executing update");
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }

    public int fetchCount(String sql, List<Object> params) {
        AppLogger.info("Executing count statement: " + sql);
        Connection connection = null;

        try {
            connection = Server.getConnectionPool().borrowConnection();
            PreparedStatement stmt = prepareStatement(connection, sql, params);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                AppLogger.info("Count executed, result found");
                return resultSet.getInt(1);
            } else {
                AppLogger.info("Count executed, no result found");
                return 0;
            }

        } catch (SQLException e) {
            AppLogger.severe("SQLException in GenericRepository.fetchCount", e);
            throw new SimpleException(500, "Error fetching count");
        } finally {
            Server.getConnectionPool().returnConnection(connection);
        }
    }
}

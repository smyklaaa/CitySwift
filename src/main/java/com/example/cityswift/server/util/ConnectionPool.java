package com.example.cityswift.server.util;



import com.example.cityswift.server.config.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final BlockingQueue<Connection> pool;

    public ConnectionPool() throws SQLException {
        AppLogger.info("Creating connection pool with size: " + DbConfig.getMaxPoolSize() + " connections");
        pool = new ArrayBlockingQueue<>(DbConfig.getMaxPoolSize());
        for (int i = 0; i < DbConfig.getMaxPoolSize(); i++) {
            pool.add(createConnection());
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                DbConfig.getDatabaseUrl(),
                DbConfig.getDatabaseUser(),
                DbConfig.getDatabasePassword());
    }

    public Connection borrowConnection() {
        AppLogger.info("Borrowing connection, available: " + pool.size() + " connections");
        try {
            return pool.take();
        } catch (InterruptedException e) {
            AppLogger.warning("Thread was interrupted while borrowing a connection.");
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void returnConnection(Connection connection) {
        if (connection != null) {
            AppLogger.info("Returning connection to pool, available: " + pool.size() + " connections");
            pool.offer(connection);
        } else {
            AppLogger.warning("Connection is null");
        }
    }

}


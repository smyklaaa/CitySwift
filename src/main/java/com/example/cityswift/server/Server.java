package com.example.cityswift.server;


import com.example.cityswift.dto.ServerResponse;
import com.example.cityswift.server.service.UserService;
import com.example.cityswift.server.util.AppLogger;
import com.example.cityswift.server.util.ClientHandlerCallable;
import com.example.cityswift.server.util.ConnectionPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ConnectionPool connectionPool;
    private static final int PORT = 8081;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) throws SQLException {
        AppLogger.info("Starting application");
        connectionPool = new ConnectionPool();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            AppLogger.info("Server started on port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                AppLogger.info("New client connected");
                Callable<ServerResponse> clientTask = new ClientHandlerCallable(clientSocket);
                executorService.submit(clientTask);
            }
        } catch (IOException e) {
            AppLogger.warning("Server exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
        }
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
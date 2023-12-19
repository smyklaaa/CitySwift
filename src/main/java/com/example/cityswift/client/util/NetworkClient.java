package com.example.cityswift.client.util;



import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class NetworkClient {
    private static final String serverAddress = "127.0.0.1";
    private static final int serverPort = 8081;

    public NetworkClient() {
    }

//    public ServerResponse sendRequest(ClientRequest request) throws Exception {
//        try (Socket socket = new Socket(serverAddress, serverPort);
//             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
//
//            outputStream.writeObject(request);
//            return (ServerResponse) inputStream.readObject();
//        }
//    }

    public static ServerResponse sendRequest(ClientRequest request) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            outputStream.writeObject(request);
            return (ServerResponse) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
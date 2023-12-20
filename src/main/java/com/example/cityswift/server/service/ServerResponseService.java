package com.example.cityswift.server.service;




import com.example.cityswift.dto.ServerResponse;

import java.io.Serializable;

public class ServerResponseService {
    public static ServerResponse createServerResponse(int resultCode, String resultMessage, Serializable data) {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setResultCode(resultCode);
        serverResponse.setResultMessage(resultMessage);
        serverResponse.setData(data);

        return serverResponse;
    }

    public static ServerResponse createPositiveServerResponse(Serializable data) {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setResultCode(200);
        serverResponse.setResultMessage("OK");
        serverResponse.setData(data);

        return serverResponse;
    }

    public static ServerResponse notFoundServerResponse () {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setResultCode(404);
        serverResponse.setResultMessage("Not found");

        return serverResponse;
    }

    public static ServerResponse serverErrorResponse () {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setResultCode(500);
        serverResponse.setResultMessage("Server error");

        return serverResponse;
    }

    public static ServerResponse userExistErrorResponse () {
        ServerResponse serverResponse = new ServerResponse();

        serverResponse.setResultCode(409);
        serverResponse.setResultMessage("User already exist in database");

        return serverResponse;
    }
}

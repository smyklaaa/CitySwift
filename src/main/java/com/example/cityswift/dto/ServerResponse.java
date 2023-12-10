package com.example.cityswift.dto;

import java.io.Serial;
import java.io.Serializable;

public class ServerResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int resultCode;
    private String resultMessage;
    private Serializable data;

    public ServerResponse() {
    }

    public ServerResponse(int resultCode, String resultMessage, Serializable data) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", data=" + data +
                '}';
    }
}

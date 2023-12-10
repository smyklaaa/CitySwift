package com.example.cityswift.dto;

import java.io.Serializable;

public class UserToken implements Serializable {

    private int token;

    public UserToken(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
package com.example.cityswift.dto;

import java.io.Serializable;

public class UserToken implements Serializable {

    private Integer token;

    public UserToken(Integer token) {
        this.token = token;
    }

    public Integer getToken() {
        return token;
    }
}
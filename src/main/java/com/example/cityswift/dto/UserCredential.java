package com.example.cityswift.dto;

import java.io.Serializable;

public class UserCredential implements Serializable {
    private final String mail;
    private final String password;

    public UserCredential(String username, String password) {
        this.mail = username;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}

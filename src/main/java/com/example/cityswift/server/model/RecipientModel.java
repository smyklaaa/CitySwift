package com.example.cityswift.server.model;

import java.io.Serializable;

public class RecipientModel implements Serializable {
    private String id;
    private String mobile;
    private String mail;

    public RecipientModel() {
    }

    public RecipientModel(String mobile, String mail) {
        this.mobile = mobile;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

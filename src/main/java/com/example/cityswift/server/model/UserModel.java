package com.example.cityswift.server.model;

import java.io.Serializable;
import java.time.LocalDate;

public class UserModel implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String publicToken;
    private Integer privateToken;
    private String mail;
    private String mobile;
    private LocalDate dateOfBirth;

    public UserModel() {
    }

    public UserModel(Integer privateToken) {
        this.privateToken = privateToken;
    }

    public UserModel(int id, String firstName, String lastName, String publicToken, String mail, String mobile, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.publicToken = publicToken;
        this.mail = mail;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPublicToken() {
        return publicToken;
    }

    public Integer getPrivateToken() {
        return privateToken;
    }
}
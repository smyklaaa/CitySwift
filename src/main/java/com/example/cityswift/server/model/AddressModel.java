package com.example.cityswift.server.model;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private int id;
    private String street;
    private String postalCode;
    private String homeNumber;
    private String doorKey;
    private String city;
    private boolean isMain;

    public AddressModel() {
    }

    public AddressModel(int id, String street, String postalCode, String homeNumber, String doorKey, boolean isMain) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.doorKey = doorKey;
        this.isMain = isMain;
    }

    public AddressModel(int id, String street, String postalCode, String homeNumber, String doorKey, String city, boolean isMain) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.doorKey = doorKey;
        this.city = city;
        this.isMain = isMain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getDoorKey() {
        return doorKey;
    }

    public void setDoorKey(String doorKey) {
        this.doorKey = doorKey;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

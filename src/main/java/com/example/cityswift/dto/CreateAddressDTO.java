package com.example.cityswift.dto;

import java.io.Serializable;

public class CreateAddressDTO implements Serializable {
    private String street;
    private String postalCode;
    private String city;
    private String homeNumber;
    private String doorKey;

    public CreateAddressDTO() {
    }

    public CreateAddressDTO(String street, String postalCode, String city, String homeNumber, String doorKey) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.homeNumber = homeNumber;
        this.doorKey = doorKey;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}

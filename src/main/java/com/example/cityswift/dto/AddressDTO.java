package com.example.cityswift.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private String postalCode;
    private String city;
    private String streetName;
    private String homeNr;
    private String doorKey;

    public AddressDTO(String postalCode, String city, String streetName, String homeNr, String doorKey) {
        this.postalCode = postalCode;
        this.city = city;
        this.streetName = streetName;
        this.homeNr = homeNr;
        this.doorKey = doorKey;
    }

    public AddressDTO() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public AddressDTO postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressDTO streetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public AddressDTO homeNr(String homeNr) {
        this.homeNr = homeNr;
        return this;
    }

    public String getHomeNr() {
        return homeNr;
    }

    public void setHomeNr(String homeNr) {
        this.homeNr = homeNr;
    }

    public AddressDTO doorKey(String doorKey) {
        this.doorKey = doorKey;
        return this;
    }

    public String getDoorKey() {
        return doorKey;
    }

    public void setDoorKey(String doorKey) {
        this.doorKey = doorKey;
    }
}

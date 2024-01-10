package com.example.cityswift.dto;

import java.io.Serializable;

public class HomeViewDetailsDTO implements Serializable {
    private BasicUserData basicUserData;
    private String pricePerKm;
    private int totalNumberOfOrders;
    private int yourNumberOfOrders;

    public HomeViewDetailsDTO() {
    }

    public BasicUserData getBasicUserData() {
        return basicUserData;
    }

    public void setBasicUserData(BasicUserData basicUserData) {
        this.basicUserData = basicUserData;
    }

    public String getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(String pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public int getTotalNumberOfOrders() {
        return totalNumberOfOrders;
    }

    public void setTotalNumberOfOrders(int totalNumberOfOrders) {
        this.totalNumberOfOrders = totalNumberOfOrders;
    }

    public int getYourNumberOfOrders() {
        return yourNumberOfOrders;
    }

    public void setYourNumberOfOrders(int yourNumberOfOrders) {
        this.yourNumberOfOrders = yourNumberOfOrders;
    }
}

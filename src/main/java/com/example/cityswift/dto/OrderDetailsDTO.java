package com.example.cityswift.dto;

import java.io.Serializable;

public class OrderDetailsDTO implements Serializable {
    private String orderId;
    private String message;
    private Double price;

    private Double packageHeight;
    private Double packageWidth;
    private Double packageDepth;
    private Double packageWeight;

    private String recipientMail;
    private String recipientMobile;

    private String recipientStreet;
    private String recipientCity;
    private String recipientHomeNumber;

    private String senderStreet;
    private String senderCity;
    private String senderHomeNumber;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String message, Double price, Double packageHeight, Double packageWidth, Double packageDepth, Double packageWeight, String recipientMail, String recipientMobile, String recipientStreet, String recipientCity, String recipientHomeNumber, String senderStreet, String senderCity, String senderHomeNumber) {
        this.message = message;
        this.price = price;
        this.packageHeight = packageHeight;
        this.packageWidth = packageWidth;
        this.packageDepth = packageDepth;
        this.packageWeight = packageWeight;
        this.recipientMail = recipientMail;
        this.recipientMobile = recipientMobile;
        this.recipientStreet = recipientStreet;
        this.recipientCity = recipientCity;
        this.recipientHomeNumber = recipientHomeNumber;
        this.senderStreet = senderStreet;
        this.senderCity = senderCity;
        this.senderHomeNumber = senderHomeNumber;
    }

    public OrderDetailsDTO(String orderId, String message, Double price, Double packageHeight, Double packageWidth, Double packageDepth, Double packageWeight, String recipientMail, String recipientMobile, String recipientStreet, String recipientCity, String recipientHomeNumber, String senderStreet, String senderCity, String senderHomeNumber) {
        this.orderId = orderId;
        this.message = message;
        this.price = price;
        this.packageHeight = packageHeight;
        this.packageWidth = packageWidth;
        this.packageDepth = packageDepth;
        this.packageWeight = packageWeight;
        this.recipientMail = recipientMail;
        this.recipientMobile = recipientMobile;
        this.recipientStreet = recipientStreet;
        this.recipientCity = recipientCity;
        this.recipientHomeNumber = recipientHomeNumber;
        this.senderStreet = senderStreet;
        this.senderCity = senderCity;
        this.senderHomeNumber = senderHomeNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(Double packageHeight) {
        this.packageHeight = packageHeight;
    }

    public Double getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(Double packageWidth) {
        this.packageWidth = packageWidth;
    }

    public Double getPackageDepth() {
        return packageDepth;
    }

    public void setPackageDepth(Double packageDepth) {
        this.packageDepth = packageDepth;
    }

    public Double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Double packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getRecipientMail() {
        return recipientMail;
    }

    public void setRecipientMail(String recipientMail) {
        this.recipientMail = recipientMail;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }

    public String getRecipientStreet() {
        return recipientStreet;
    }

    public void setRecipientStreet(String recipientStreet) {
        this.recipientStreet = recipientStreet;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientHomeNumber() {
        return recipientHomeNumber;
    }

    public void setRecipientHomeNumber(String recipientHomeNumber) {
        this.recipientHomeNumber = recipientHomeNumber;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderHomeNumber() {
        return senderHomeNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setSenderHomeNumber(String senderHomeNumber) {
        this.senderHomeNumber = senderHomeNumber;
    }
}

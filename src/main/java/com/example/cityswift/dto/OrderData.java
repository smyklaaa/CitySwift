package com.example.cityswift.dto;

import java.io.Serializable;

public class OrderData implements Serializable {

    private Integer orderId;
    private Integer senderId;
    private Integer courierId;
    private Integer price;
    private Integer packageId;
    private  Integer status;
    private Integer recipientId;
    private String message;
    private String firstName;
    private String lastName;

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

    public OrderData(Integer packageId, Integer senderId, Integer courierId, Integer price, Integer orderId,
                     Integer status, Integer recipientId, String message, String firstName, String lastName) {
        this.packageId = packageId;
        this.senderId = senderId;
        this.courierId = courierId;
        this.price = price;
        this.orderId = orderId;
        this.status = status;
        this.recipientId = recipientId;
        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
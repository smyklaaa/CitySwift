package com.example.cityswift.server.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderModel implements Serializable {
    private Integer id;
    private Integer senderId;
    private Integer recipientId;
    private Integer courierId;
    private BigDecimal price;
    private Integer packageId;
    private Integer statusId;
    private String message;
    private String firstName;
    private String lastName;
    private String mail;
    private int orderCode;


    public OrderModel(Integer id, Integer senderId, Integer recipientId, Integer courierId, BigDecimal price,
                      Integer packageId, Integer statusId, String message, String firstName, String lastName, int orderCode) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.courierId = courierId;
        this.price = price;
        this.packageId = packageId;
        this.statusId = statusId;
        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderCode = orderCode;
    }

    public OrderModel(Integer id, Integer senderId, Integer recipientId, Integer courierId, BigDecimal price,
                      Integer packageId, Integer statusId, String message) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.courierId = courierId;
        this.price = price;
        this.packageId = packageId;
        this.statusId = statusId;
        this.message = message;
    }

    public OrderModel(int id, int senderId, int recipientId, int courierId, BigDecimal price, int packageId,
                      int statusId, String message, String mail) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.courierId = courierId;
        this.price = price;
        this.packageId = packageId;
        this.statusId = statusId;
        this.message = message;
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public void setMail(String mail) {
        this.mail = mail;


    }
}


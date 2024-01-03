package com.example.cityswift.dto;

import com.example.cityswift.server.model.AddressModel;
import com.example.cityswift.server.model.OrderModel;
import com.example.cityswift.server.model.PackageModel;
import com.example.cityswift.server.model.RecipientModel;

import java.io.Serializable;

public class CreateOrderRequest implements Serializable {
    private OrderModel orderModel;
    private PackageModel packageModel;
    private AddressModel addressModel;
    private RecipientModel recipientModel;
    private String message;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(OrderModel orderModel, PackageModel packageModel, AddressModel addressModel, RecipientModel recipientModel, String message) {
        this.orderModel = orderModel;
        this.packageModel = packageModel;
        this.addressModel = addressModel;
        this.recipientModel = recipientModel;
        this.message = message;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public PackageModel getPackageModel() {
        return packageModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public RecipientModel getRecipientModel() {
        return recipientModel;
    }

    public String getMessage() {
        return message;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public void setPackageModel(PackageModel packageModel) {
        this.packageModel = packageModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public void setRecipientModel(RecipientModel recipientModel) {
        this.recipientModel = recipientModel;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

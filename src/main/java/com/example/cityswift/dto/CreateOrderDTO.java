package com.example.cityswift.dto;

import java.io.Serializable;

public class CreateOrderDTO implements Serializable {
    private CreatePackageDTO createPackageDTO;
    private Integer senderId;
    private String message;
    private String recipientMobile;
    private String recipientMail;
    private CreateAddressDTO createAddressDTO;


    public CreateOrderDTO() {
    }

    public CreatePackageDTO getCreatePackageDTO() {
        return createPackageDTO;
    }

    public void setCreatePackageDTO(CreatePackageDTO createPackageDTO) {
        this.createPackageDTO = createPackageDTO;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }

    public String getRecipientMail() {
        return recipientMail;
    }

    public void setRecipientMail(String recipientMail) {
        this.recipientMail = recipientMail;
    }

    public CreateAddressDTO getCreateAddressDTO() {
        return createAddressDTO;
    }

    public void setCreateAddressDTO(CreateAddressDTO createAddressDTO) {
        this.createAddressDTO = createAddressDTO;
    }

    public void getCreateAddressDTO(CreateAddressDTO createAddressDTO) {
        this.createAddressDTO = createAddressDTO;
    }
}
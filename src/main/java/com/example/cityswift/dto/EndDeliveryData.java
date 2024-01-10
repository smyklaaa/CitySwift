package com.example.cityswift.dto;

import java.io.Serializable;

public class EndDeliveryData implements Serializable {
    private int code;
    private int orderId;

    public EndDeliveryData(int code, int orderId) {
        this.code = code;
        this.orderId = orderId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

package com.example.cityswift.dto;

import java.io.Serializable;

public class ParticularArea implements Serializable {
    private final String area;
    private String[] listOfUsersInArea;

    public ParticularArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

}

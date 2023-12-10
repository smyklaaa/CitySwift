package com.example.cityswift.dto;



import com.example.cityswift.server.model.AddressModel;

import java.io.Serializable;
import java.util.List;

public class ListOfAddressInArea implements Serializable {
    public ListOfAddressInArea(List<AddressModel> listOfAddressInArea) {
        this.listOfAddressInArea = listOfAddressInArea;
    }

    public List<AddressModel> getListOfAddressInArea() {
        return listOfAddressInArea;
    }

    public void setListOfAddressInArea(List<AddressModel> listOfAddressInArea) {
        this.listOfAddressInArea = listOfAddressInArea;
    }

    List<AddressModel> listOfAddressInArea;
}

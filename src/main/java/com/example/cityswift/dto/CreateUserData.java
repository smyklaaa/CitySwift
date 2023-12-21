package com.example.cityswift.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CreateUserData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String mobile;
    private LocalDate dateOfBirth;
    private String street;
    private String postalCode;
    private String homeNr;
    private String doorKeyNr;
    private String city;

    public CreateUserData(String firstName, String lastName, String mail, String password, String mobile,String city,
                          String street, String postalCode, String homeNr, String doorKeyNr, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.postalCode = postalCode;
        this.homeNr = homeNr;
        this.doorKeyNr = doorKeyNr;
        this.city = city;
    }

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getPostalCode() {return postalCode;}

    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public String getHomeNr() { return homeNr;}

    public void setHomeNr(String homeNr) { this.homeNr = homeNr;}

    public String getDoorKeyNr() { return doorKeyNr;}

    public void setDoorKeyNr(String doorKeyNr) { this.doorKeyNr = doorKeyNr;}
}


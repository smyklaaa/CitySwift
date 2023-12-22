package com.example.cityswift.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SendPackageController {

    @FXML
    private TextField mailTextField;
    @FXML
    private TextField mobileTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField depthTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField streetNameTextField;
    @FXML
    private TextField homeNrTextField;
    @FXML
    private TextField doorKeyTextField;
    @FXML
    private Label reactionMessageLabel;
    @FXML
    private Label incorrectDataMessageLabel;

    public void sendPackageButtonOn(ActionEvent event) {
    }
}

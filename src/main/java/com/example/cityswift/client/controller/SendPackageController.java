package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.*;
import com.example.cityswift.server.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

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

    @FXML
    private ComboBox<BasicUserData> friendList;

    private void setFriend(BasicUserData selectedUser) {
        mailTextField.setText(selectedUser.getUserModel().getMail());
        mobileTextField.setText(selectedUser.getUserModel().getMobile());
        postalCodeTextField.setText(selectedUser.getAddressModel().get(0).getPostalCode());
        cityTextField.setText(selectedUser.getAddressModel().get(0).getCity());
        streetNameTextField.setText(selectedUser.getAddressModel().get(0).getStreet());
        homeNrTextField.setText(selectedUser.getAddressModel().get(0).getHomeNumber());
        doorKeyTextField.setText(selectedUser.getAddressModel().get(0).getDoorKey());
    }

    @FXML
    private void initialize() {
        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("getFriends", true, UserSession.getUserToken().getToken()));
        List<BasicUserData> friends = (List<BasicUserData>) serverResponse.getData();

        friendList.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(BasicUserData data, boolean empty) {
                super.updateItem(data, empty);
                if (data == null || empty) {
                    setText(null);
                } else {
                    UserModel user = data.getUserModel();
                    setText(user.getFirstName() + " " + user.getLastName());
                }
            }
        });

        friendList.setOnAction(event -> {
            BasicUserData selectedUser = friendList.getValue();
            setFriend(selectedUser);
        });

        friendList.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(BasicUserData data, boolean empty) {
                super.updateItem(data, empty);
                if (data == null || empty) {
                    setText(null);
                } else {
                    UserModel user = data.getUserModel();
                    setText(user.getFirstName() + " " + user.getLastName());
                }
            }
        });

        for (BasicUserData friend : friends) {
            friendList.getItems().add(friend);
        }
    }

    public void sendPackageButtonOn(ActionEvent event) {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        CreatePackageDTO createPackageDTO = new CreatePackageDTO();
        createPackageDTO.setHeight(Integer.parseInt(heightTextField.getText()));
        createPackageDTO.setWidth(Integer.parseInt(widthTextField.getText()));
        createPackageDTO.setDepth(Integer.parseInt(depthTextField.getText()));
        createPackageDTO.setWeight(Integer.parseInt(weightTextField.getText()));
        createOrderDTO.setCreatePackageDTO(createPackageDTO);

        createOrderDTO.setSenderId(UserSession.getUserToken().getToken());
        createOrderDTO.setRecipientMail(mailTextField.getText());
        createOrderDTO.setRecipientMobile(mobileTextField.getText());

        CreateAddressDTO createAddressDTO = new CreateAddressDTO();
        createAddressDTO.setPostalCode(postalCodeTextField.getText());
        createAddressDTO.setCity(cityTextField.getText());
        createAddressDTO.setStreet(streetNameTextField.getText());
        createAddressDTO.setHomeNumber(homeNrTextField.getText());
        createAddressDTO.setDoorKey(doorKeyTextField.getText());
        createOrderDTO.setCreateAddressDTO(createAddressDTO);

        createOrderDTO.setMessage("test");

        ServerResponse serverResponse = NetworkClient.sendRequest(new ClientRequest("createOrder", createOrderDTO, UserSession.getUserToken().getToken()));
    }
}

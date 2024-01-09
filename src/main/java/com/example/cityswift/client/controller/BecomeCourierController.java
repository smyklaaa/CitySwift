package com.example.cityswift.client.controller;

import com.example.cityswift.client.util.NetworkClient;
import com.example.cityswift.client.util.SceneSwitcher;
import com.example.cityswift.client.util.UserSession;
import com.example.cityswift.dto.ClientRequest;
import com.example.cityswift.dto.OrderDetailsDTO;
import com.example.cityswift.dto.ServerResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BecomeCourierController {
    @FXML
    private void showOrderDetails(ActionEvent event) throws IOException {
        createPopUp();
    }

    @FXML
    private ListView<String> packageList;

    @FXML
    private void initialize() {
        updateOrderList();
    }

    private void updateOrderList() {
        ServerResponse serverResponse = NetworkClient.sendRequest
                (new ClientRequest("getOrderList", 1, UserSession.getUserToken().getToken()));
        packageList.getItems().clear();

        List<OrderDetailsDTO> orderDetailsDTOList = (List<OrderDetailsDTO>) serverResponse.getData();

        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOList) {
            String displayText =
                    "ID: " + orderDetailsDTO.getOrderId() + " Miejsce odbioru: " + orderDetailsDTO.getSenderCity() + " " + orderDetailsDTO.getSenderStreet() + " " + orderDetailsDTO.getSenderHomeNumber() +
                            " -> Miejsce dostawy" + orderDetailsDTO.getRecipientCity() + " " + orderDetailsDTO.getRecipientStreet() + " " + orderDetailsDTO.getRecipientHomeNumber();
            packageList.getItems().add(displayText);
        }
    }

    public void createPopUp() {
        String selectedPackage = packageList.getSelectionModel().getSelectedItem();
        String[] selectedPackageSplit = selectedPackage.split(" ");
        String packageId = selectedPackageSplit[1];

        ServerResponse serverResponse = NetworkClient.sendRequest
                (new ClientRequest("getOrderById", Integer.parseInt(packageId), UserSession.getUserToken().getToken()));

        if (serverResponse.getData() == null) {
            return;
        }

        OrderDetailsDTO orderDetailsDTO = (OrderDetailsDTO) serverResponse.getData();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Formularz");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        grid.add(new Label("Z: "), 0, 0);
        grid.add(new Label(orderDetailsDTO.getRecipientCity() + " " + orderDetailsDTO.getRecipientStreet() + " " + orderDetailsDTO.getRecipientStreet()), 1, 0);

        grid.add(new Label("Do: "), 0, 1);
        grid.add(new Label(orderDetailsDTO.getSenderCity() + " " + orderDetailsDTO.getSenderStreet() + " " + orderDetailsDTO.getSenderStreet()), 1, 1);

        grid.add(new Label("Waga: "), 0, 2);
        grid.add(new Label(orderDetailsDTO.getPackageHeight() + " kg"), 1, 2);

        grid.add(new Label("Wymiary: "), 0, 3);
        grid.add(new Label(orderDetailsDTO.getPackageHeight() + " x " + orderDetailsDTO.getPackageWidth() + " x " + orderDetailsDTO.getPackageDepth()), 1, 3);

        grid.add(new Label("Opis: "), 0, 4);
        grid.add(new Label(orderDetailsDTO.getMessage()), 1, 4);

        grid.add(new Label("Cena: "), 0, 5);
        grid.add(new Label(orderDetailsDTO.getPrice() + " zł"), 1, 5);

        Button closeButton = new Button("Weź paczkę");
        closeButton.setOnAction(e -> takePackage(orderDetailsDTO.getOrderId(), popupStage));

        grid.add(closeButton, 0, 6);

        Scene scene = new Scene(grid, 300, 250);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    private void takePackage(String packageId, Stage stage) {
        NetworkClient.sendRequest(new ClientRequest("takePackage", packageId, UserSession.getUserToken().getToken()));
        stage.close();
    }

}

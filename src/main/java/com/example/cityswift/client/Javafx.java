package com.example.cityswift.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Javafx extends Application {
    public static void main(String[] args) {
        launch();
    }

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Javafx.class.getResource("/view/login/loginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 749, 484);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/logo_white.png")));
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/view/login/loginStyle.css")).toExternalForm());
            stage.getIcons().add(icon);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/common/commonStyle.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
}
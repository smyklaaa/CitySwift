module com.example.cityswift {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.cityswift.client to javafx.fxml;
    opens com.example.cityswift.client.controller to javafx.fxml;
    exports com.example.cityswift.client;
}
module com.example.cityswift {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cityswift to javafx.fxml;
    exports com.example.cityswift;
}
module com.example.rentahome {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rentahome to javafx.fxml;
    exports com.example.rentahome;
}
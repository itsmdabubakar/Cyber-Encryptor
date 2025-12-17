module com.example.cyberencryptor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cyberencryptor to javafx.fxml;
    exports com.example.cyberencryptor;
}
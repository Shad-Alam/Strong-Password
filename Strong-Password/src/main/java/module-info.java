module com.example.strongpassword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.strongpassword to javafx.fxml;
    exports com.example.strongpassword;
}
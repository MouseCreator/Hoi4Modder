module com.example.hoi4modder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hoi4modder to javafx.fxml;
    exports com.example.hoi4modder;
}
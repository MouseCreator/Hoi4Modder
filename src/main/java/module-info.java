module com.example.hoi4modder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires javafx.swing;

    opens com.example.hoi4modder to javafx.fxml;
    exports com.example.hoi4modder;
    exports com.example.hoi4modder.controller;
    exports com.example.hoi4modder.game.roles;
    exports com.example.hoi4modder.model.files.iovisitor;
    exports com.example.hoi4modder.service;
    opens com.example.hoi4modder.controller to javafx.fxml;
}
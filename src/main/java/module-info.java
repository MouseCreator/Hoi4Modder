module com.example.hoi4modder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires javafx.swing;
    requires org.controlsfx.controls;

    opens com.example.hoi4modder to javafx.fxml;
    opens com.example.hoi4modder.controller to javafx.fxml;

    exports com.example.hoi4modder;
    exports com.example.hoi4modder.game.common;
    exports com.example.hoi4modder.controller.requests;
    exports com.example.hoi4modder.controller;
    exports com.example.hoi4modder.game.roles;
    exports com.example.hoi4modder.model.files.iovisitor;
    exports com.example.hoi4modder.service;
    exports com.example.hoi4modder.game;
    exports com.example.hoi4modder.game.collection;
    exports com.example.hoi4modder.model.files.properties;
    exports com.example.hoi4modder.model.files.properties.factories;
    exports com.example.hoi4modder.model.files.maps;
    exports com.example.hoi4modder.model.files.properties.lists;
    exports com.example.hoi4modder.model.files.properties.styles;
    exports com.example.hoi4modder.model.files.manager;
    exports com.example.hoi4modder.model.files.manager.strategy;
    exports com.example.hoi4modder.controller.autocomplete;
    exports com.example.hoi4modder.controller.character_extra;
    exports com.example.hoi4modder.controller.command;
    exports com.example.hoi4modder.controller.command.roles;
    opens com.example.hoi4modder.controller.character_extra to javafx.fxml;
    exports com.example.hoi4modder.controller.command.history;
}

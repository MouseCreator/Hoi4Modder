package com.example.hoi4modder.controller.command.roles;

import com.example.hoi4modder.controller.command.ControlConnectable;
import com.example.hoi4modder.controller.requests.RequestHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;

public class AutomaticInitializer<T> {
    public void initialize(RequestHandler<T> handler, ControlConnectable controller) {
        Class<?> controllerClass = controller.getClass();
        Field[] fields = controllerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FXML.class)) {
                field.setAccessible(true);
                Object obj;
                try {
                    obj = field.get(controller);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (TextField.class.isAssignableFrom(field.getType())) {
                     handler.handleConnect((TextField) obj, controller.callSelf());
                } else  if (CheckBox.class.isAssignableFrom(field.getType())) {
                    handler.handleConnect((CheckBox) obj, controller.callSelf());
                } else  if (ComboBox.class.isAssignableFrom(field.getType())) {
                    @SuppressWarnings( "unchecked" )
                    ComboBox<String> box = (ComboBox<String>) obj;
                    handler.handleConnect(box, controller.callSelf());
                }
            }
        }
    }
}

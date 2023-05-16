package com.example.hoi4modder.controller.command;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.lang.reflect.Field;
import java.util.List;

public class ControlIndexConnector {
    private final List<Object> controls;

    private boolean isInitialized;

    public ControlIndexConnector(List<Object> list) {
        controls = list;
    }
    public void initialize(Initializable controller) {
        if (isInitialized) {
            throw new IllegalStateException("The connector has already benn initialized");
        }
        assert controls != null;
        Class<?> controllerClass = controller.getClass();
        Field[] fields = controllerClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FXML.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(controller);
                    controls.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        isInitialized = true;
    }

    public Object getFieldByIndex(int index) {
        if (!isInitialized)
            throw new IllegalStateException("Connector is not initialized");
        return controls.get(index);
    }
    public int getIndexOf(Object object) {
        if (!isInitialized)
            throw new IllegalStateException("Connector is not initialized!");
        return controls.indexOf(object);
    }
}

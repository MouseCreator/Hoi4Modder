package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.AdvisorRoleController;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ControlIndexConnectorTest {
    private final JFXPanel panel = new JFXPanel(); //Simulate start of the application
    @Test
    void testInvariance() {
        assertNull(panel.getScene());
        ControlIndexConnector connector1 = ControlIndexConnector.getArrayConnector();
        Initializable controller1 = loadAdvisor();
        connector1.initialize(controller1);
        assertThrows(IllegalStateException.class,() -> connector1.initialize(loadAdvisor()));

        ControlIndexConnector connector2 = ControlIndexConnector.getArrayConnector();

        Initializable controller2 = loadAdvisor();
        connector2.initialize(controller2);

        assertEquals(connector1.size(), connector2.size());
        for (int i = 0; i < connector1.size(); i++) {
            Object value1 = connector1.getFieldByIndex(i);
            Object value2 = connector2.getFieldByIndex(i);
            assertEquals(fieldName(controller1,value1), fieldName(controller2, value2));
        }
    }

    private String fieldName(Object controller, Object value){
        Class<?> myClass = controller.getClass();
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(controller);
                if (fieldValue != null && fieldValue.equals(value)) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("No value " + value + "in controller " + controller);
    }

    private AdvisorRoleController loadAdvisor() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AdvisorRoleController.class.getResource("advisor-item.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
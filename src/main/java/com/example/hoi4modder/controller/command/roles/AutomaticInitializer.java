package com.example.hoi4modder.controller.command.roles;

import com.example.hoi4modder.controller.command.ControlConnectable;
import com.example.hoi4modder.controller.requests.RequestHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.List;

public class AutomaticInitializer<T> {
    public UndoRedoManager initialize(RequestHandler<T> handler, ControlConnectable controller) {
        Class<?> controllerClass = controller.getClass();
        Field[] fields = controllerClass.getDeclaredFields();
        UndoRedoManager undoRedoManager = UndoRedoManagerFactory.get().treeMap();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FXML.class)) {
                handleFXMLAnnotation(handler, controller, field, undoRedoManager);
            }
        }
        return undoRedoManager;
    }
    public UndoRedoManager initialize(RequestHandler<T> handler, ControlConnectable controller, String[] exceptions) {
        Class<?> controllerClass = controller.getClass();
        Field[] fields = controllerClass.getDeclaredFields();
        UndoRedoManager undoRedoManager = UndoRedoManagerFactory.get().treeMap();
        for (Field field : fields) {
            if (List.of(exceptions).contains(field.getName()))
                continue;
            if (field.isAnnotationPresent(FXML.class)) {
                handleFXMLAnnotation(handler, controller, field, undoRedoManager);
            }
        }
        return undoRedoManager;
    }

    private void handleFXMLAnnotation(RequestHandler<T> handler, ControlConnectable controller, Field field,
                                      UndoRedoManager undoRedoManager) {
        field.setAccessible(true);
        Object obj;
        try {
            obj = field.get(controller);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        undoRedoManager.addObjectStatus(obj);
        if (TextField.class.isAssignableFrom(field.getType())) {
             handler.handleConnect((TextField) obj, controller.callSelf(), undoRedoManager);
        } else  if (CheckBox.class.isAssignableFrom(field.getType())) {
            handler.handleConnect((CheckBox) obj, controller.callSelf(), undoRedoManager);
        } else  if (ComboBox.class.isAssignableFrom(field.getType())) {
            @SuppressWarnings( "unchecked" )
            ComboBox<String> box = (ComboBox<String>) obj;
            handler.handleConnect(box, controller.callSelf(), undoRedoManager);
        }
    }
}

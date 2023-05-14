package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.List;

public interface EditorListTask {
    /**
     *
     * @return controllers for generated list items
     */
    List<CharacterItemController> getControllers();

    /**
     *
     * @return generated user interface list items
     */
    ObservableList<Pane> getPanes();
}

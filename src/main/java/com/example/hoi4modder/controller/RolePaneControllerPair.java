package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.Role;
import javafx.scene.layout.Pane;
import lombok.Data;

@Data
public class RolePaneControllerPair {
    private Pane pane;
    private RoleController<Role> roleController;

    public void clear() {
        pane = null;
        roleController = null;
    }
    public boolean isFilled() {
        return pane == null;
    }
}

package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.Role;
import javafx.scene.layout.Pane;

public class RolePaneControllerPair<R extends Role> {
    private Pane pane;
    private RoleController<R> roleController;

    public void update(Pane pane, RoleController<R> roleController) {
        this.pane = pane;
        this.roleController = roleController;
    }

    public RoleController<R> getController() {
        return roleController;
    }
    public Pane getPane() {
        return pane;
    }
    public void clear() {
        pane = null;
        roleController = null;
    }
    public boolean isFilled() {
        return pane != null;
    }
}

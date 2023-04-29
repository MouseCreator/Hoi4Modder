package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.Role;
import javafx.scene.layout.Pane;
import lombok.Data;

@Data
public class RolePaneControllerPair {
    private Pane pane;
    private RoleController<Role> roleController;
}

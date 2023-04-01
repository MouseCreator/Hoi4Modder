package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.Role;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class NavyLeaderController extends RoleController<NavyLeader> implements Initializable {
    @Override
    public String filename() {
        return "navy-leader-item.fxml";
    }

    @Override
    public void fromRole(NavyLeader role) {

    }

    @Override
    public NavyLeader toRole() {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

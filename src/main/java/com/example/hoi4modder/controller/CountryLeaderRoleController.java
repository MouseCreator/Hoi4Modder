package com.example.hoi4modder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CountryLeaderRoleController extends RoleController{

    @FXML
    private TextField ideologyField;

    @FXML
    private ListView<String> traitList;

    @FXML
    private TextField traitName;

    public void addTrait() {
        super.addTrait(traitList, traitName.getText());
    }
}

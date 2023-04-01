package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.CountryLeader;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CountryLeaderRoleController extends RoleController<CountryLeader> {

    @FXML
    private TextField ideologyField;

    @FXML
    private ListView<String> traitList;

    @FXML
    private TextField traitName;

    public void addTrait() {
        super.addTrait(traitList, traitName.getText());
    }

    @Override
    public String filename() {
        return "country-leader-item.fxml";
    }

    @Override
    public void fromRole(CountryLeader leader) {
        ideologyField.setText(leader.getIdeology());
        traitList.getItems().addAll(leader.getTraits());
    }

    @Override
    public CountryLeader toRole() {
        CountryLeader leader = new CountryLeader();
        leader.setIdeology(ideologyField.getText());
        leader.setTraits(getTraits(traitList));
        return leader;
    }
}

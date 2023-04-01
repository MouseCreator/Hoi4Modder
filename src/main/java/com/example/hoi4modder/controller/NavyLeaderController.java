package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.NavyLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NavyLeaderController extends RoleController<NavyLeader> implements Initializable {

    @FXML
    private TextField attackField;

    @FXML
    private TextField cordsField;

    @FXML
    private TextField defenceField;

    @FXML
    private TextField manuverField;

    @FXML
    private TextField skillField;

    @FXML
    private TextField traitField;

    @FXML
    private ListView<String> traitsList;

    @Override
    public String filename() {
        return "navy-leader-item.fxml";
    }

    @Override
    public void fromRole(NavyLeader navyLeader) {
        skillField.setText(String.valueOf(navyLeader.getSkill()));
        attackField.setText(String.valueOf(navyLeader.getAttackSkill()));
        defenceField.setText(String.valueOf(navyLeader.getDefenceSkill()));
        manuverField.setText(String.valueOf(navyLeader.getManeuveringSkill()));
        cordsField.setText(String.valueOf(navyLeader.getCoordinationSkill()));
        traitsList.getItems().clear();
        traitsList.getItems().addAll(navyLeader.getTraits());
    }

    @Override
    public NavyLeader toRole() {
        NavyLeader navyLeader = new NavyLeader();
        navyLeader.setSkill(Integer.parseInt(skillField.getText()));
        navyLeader.setAttackSkill(Integer.parseInt(attackField.getText()));
        navyLeader.setDefenceSkill(Integer.parseInt(defenceField.getText()));
        navyLeader.setManeuveringSkill(Integer.parseInt(manuverField.getText()));
        navyLeader.setCoordinationSkill(Integer.parseInt(cordsField.getText()));
        navyLeader.setTraits(getTraits(traitsList));
        return navyLeader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavyLeader navyLeader = NavyLeader.createCountryLeader();
        fromRole(navyLeader);
    }
}

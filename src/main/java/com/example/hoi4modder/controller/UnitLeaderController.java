package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.UnitLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UnitLeaderController extends RoleController<UnitLeader> implements Initializable {

    @FXML
    private TextField attackField;

    @FXML
    private TextField defenseField;

    @FXML
    private CheckBox fieldMarshalBox;

    @FXML
    private TextField skillField;

    @FXML
    private TextField logisticsField;

    @FXML
    private TextField planningField;

    @FXML
    private TextField traitField;

    @FXML
    private ListView<String> traitsList;
    @Override
    public String filename() {
        return "unit-leader-item.fxml";
    }

    @Override
    public void fromRole(UnitLeader role) {
        skillField.setText(String.valueOf(role.getSkill()));
        attackField.setText(String.valueOf(role.getAttackSkill()));
        defenseField.setText(String.valueOf(role.getDefenceSkill()));
        logisticsField.setText(String.valueOf(role.getLogisticsSkill()));
        planningField.setText(String.valueOf(role.getPlanningSkill()));
    }

    @Override
    public UnitLeader toRole() {
        UnitLeader leader = new UnitLeader();
        leader.setSkill(Integer.parseInt(skillField.getText()));
        leader.setAttackSkill(Integer.parseInt(attackField.getText()));
        leader.setDefenceSkill(Integer.parseInt(defenseField.getText()));
        leader.setLogisticsSkill(Integer.parseInt(logisticsField.getText()));
        leader.setPlanningSkill(Integer.parseInt(planningField.getText()));
        leader.setTraits(getTraits(traitsList));
        leader.setFieldMarshal(fieldMarshalBox.isSelected());
        return leader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnitLeader templateLeader = UnitLeader.createCorpsCommander();
        fromRole(templateLeader);
    }
}

package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.CharacterRoles;
import com.example.hoi4modder.game.roles.UnitLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for unit leader
 */
public class UnitLeaderController extends RoleController<UnitLeader> implements Initializable {

    @FXML
    private TextField attackField;

    @FXML
    private TextField defenseField;

    @FXML
    private CheckBox fieldMarshalBox;

    @FXML
    private TextField logisticsField;

    @FXML
    private TextField planningField;

    @FXML
    private TextField skillField;

    @FXML
    private TextField traitField;

    @FXML
    private ListView<String> traitsList;
    @Override
    public String filename() {
        return "unit-leader-item.fxml";
    }

    private UnitLeader unitLeader = UnitLeader.createCorpsCommander();
    @Override
    public void fromRole(UnitLeader unitLeader) {
        this.unitLeader = unitLeader;
        skillField.setText(String.valueOf(unitLeader.getSkill()));
        attackField.setText(String.valueOf(unitLeader.getAttackSkill()));
        defenseField.setText(String.valueOf(unitLeader.getDefenceSkill()));
        logisticsField.setText(String.valueOf(unitLeader.getLogisticsSkill()));
        planningField.setText(String.valueOf(unitLeader.getPlanningSkill()));
        traitsList.getItems().clear();
        fieldMarshalBox.setSelected(unitLeader.getFieldMarshal());
        traitsList.getItems().addAll(unitLeader.getTraits());
    }

    @Deprecated
    public UnitLeader toRoleOld() {
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

    public UnitLeader toRole() {
       return unitLeader;
    }

    @Override
    public String getRoleType() {
        return CharacterRoles.UNIT_LEADER;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromRole(UnitLeader.createCorpsCommander());
        setValueListeners();
    }

    private void setValueListeners() {
        attackField.textProperty().addListener((observableValue, old, newValue) -> unitLeader.setAttackSkill(Integer.parseInt(newValue)));
        defenseField.textProperty().addListener((observableValue, old, newValue) -> unitLeader.setDefenceSkill(Integer.parseInt(newValue)));
        skillField.textProperty().addListener((observableValue, old, newValue) -> unitLeader.setSkill(Integer.parseInt(newValue)));
        planningField.textProperty().addListener((observableValue, old, newValue) -> unitLeader.setPlanningSkill(Integer.parseInt(newValue)));
        logisticsField.textProperty().addListener((observableValue, old, newValue) -> unitLeader.setLogisticsSkill(Integer.parseInt(newValue)));
        fieldMarshalBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> unitLeader.setFieldMarshal(t1));
    }
    public void fromCharacter(GameCharacter character) {
        if (character.getRoles().containsKey(getRoleType())) {
            this.unitLeader = (UnitLeader) character.getRoles().get(getRoleType());
        } else {
            character.getRoles().put(getRoleType(), toRole());
        }
    }
}

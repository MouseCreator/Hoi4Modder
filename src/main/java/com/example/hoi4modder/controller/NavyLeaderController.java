package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.CharacterRoles;
import com.example.hoi4modder.game.roles.NavyLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for navy leader
 */
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
    private NavyLeader navyLeader;

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
        return navyLeader;
    }

    @Override
    public String getRoleType() {
        return CharacterRoles.NAVY_LEADER;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavyLeader navyLeader = NavyLeader.createNavyLeader();
        fromRole(navyLeader);
    }
    public void fromCharacter(GameCharacter character) {
        if (character.getRoles().containsKey(getRoleType())) {
            this.navyLeader = (NavyLeader) character.getRoles().get(getRoleType());
        } else {
            character.getRoles().put(getRoleType(), toRole());
        }
    }
}

package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.command.roles.ActionRunner;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.CharacterRoles;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.utilities.IntegerValues;
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
    private TextField maneuverField;

    @FXML
    private TextField skillField;

    @FXML
    private TextField traitField;
    @FXML
    private ListView<String> traitsList;
    private NavyLeader navyLeader = NavyLeader.createNavyLeader();
    @Override
    public String filename() {
        return "navy-leader-item.fxml";
    }

    @Override
    public void fromRole(NavyLeader navyLeader) {
        ActionRunner.get().runAction(undoRedoManager,() -> {
            skillField.setText(String.valueOf(navyLeader.getSkill()));
            attackField.setText(String.valueOf(navyLeader.getAttackSkill()));
            defenceField.setText(String.valueOf(navyLeader.getDefenceSkill()));
            maneuverField.setText(String.valueOf(navyLeader.getManeuveringSkill()));
            cordsField.setText(String.valueOf(navyLeader.getCoordinationSkill()));
            traitsList.getItems().clear();
            traitsList.getItems().addAll(navyLeader.getTraits());
        });
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
        fromRole(NavyLeader.createNavyLeader());
        initializeContextMenu(traitsList);
        setValueListeners();
    }
    @Override
    public void initConnector() {
        initializeControlConnector(this);
        undoRedoManager = characterItemController.getListEditor().getHandler().handleInitialization(this);
    }
    public void fromCharacter(GameCharacter character) {
        if (character.getRoles().containsKey(getRoleType())) {
            this.navyLeader = (NavyLeader) character.getRoles().get(getRoleType());
        } else {
            character.getRoles().put(getRoleType(), toRole());
        }
    }

    private void setValueListeners() {
        attackField.textProperty().addListener((observableValue, old, newValue) -> navyLeader.setAttackSkill(IntegerValues.parseInt(newValue)));
        defenceField.textProperty().addListener((observableValue, old, newValue) -> navyLeader.setDefenceSkill(IntegerValues.parseInt(newValue)));
        skillField.textProperty().addListener((observableValue, old, newValue) -> navyLeader.setSkill(IntegerValues.parseInt(newValue)));
        maneuverField.textProperty().addListener((observableValue, old, newValue) -> navyLeader.setManeuveringSkill(IntegerValues.parseInt(newValue)));
        cordsField.textProperty().addListener((observableValue, old, newValue) -> navyLeader.setCoordinationSkill(IntegerValues.parseInt(newValue)));
    }

    @FXML
    void addTrait() {
        super.addTrait(traitsList, traitField);
    }

    @FXML
    void removeTrait() {
        super.removeTrait(traitsList);
    }

    @Override
    protected String roleString() {
        return CharacterRoles.NAVY_LEADER;
    }
}

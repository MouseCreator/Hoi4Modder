package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CharacterRoles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for advisor
 */
public class AdvisorRoleController extends RoleController<Advisor> implements Initializable {

    @FXML
    private TextField costField;

    @FXML
    private ComboBox<String> slotComboBox;

    @FXML
    private TextField traitField;

    @FXML
    private ListView<String> traitList;
    private Advisor advisor = Advisor.createAdvisor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        initializeContextMenu(traitList);
        fromRole(advisor);
        setValueListeners();
    }
    private void setValueListeners() {
        costField.textProperty().addListener((observableValue, old, newValue) -> advisor.setCost(Integer.parseInt(newValue)));
        slotComboBox.valueProperty().addListener((observableValue, old, newValue) -> advisor.setSlot(stringToLowerCase(newValue)));
    }
    private void initComboBox() {
        slotComboBox.getItems().removeAll(slotComboBox.getItems());
        slotComboBox.getItems().addAll("Political advisor", "High command", "Theorist",
                "Army chief", "Navy chief", "Air chief");
        slotComboBox.getSelectionModel().select("Political advisor");
    }



    @FXML
    void addTrait() {
        super.addTrait(traitList, traitField.getText());
    }

    @FXML
    void removeTrait() {
        super.removeTrait(traitList);
    }

    @Override
    public String filename() {
        return "advisor-item.fxml";
    }

    /**
     *
     * @param advisor - advisor to fill item fields
     */
    @Override
    public void fromRole(Advisor advisor) {
        costField.setText(String.valueOf(advisor.getCost()));
        setSelectedFromBox(advisor.getSlot(), slotComboBox);
        traitList.getItems().clear();
        traitList.getItems().addAll(advisor.getTraits());
    }
    public Advisor toRole() {
        return advisor;
    }

    @Override
    public String getRoleType() {
        return CharacterRoles.ADVISOR;
    }

    public void fromCharacter(GameCharacter character) {
        if (character.getRoles().containsKey(getRoleType())) {
            this.advisor = (Advisor) character.getRoles().get(getRoleType());
        } else {
            character.getRoles().put(getRoleType(), toRole());
        }
    }

}

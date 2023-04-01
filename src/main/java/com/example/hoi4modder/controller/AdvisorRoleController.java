package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.roles.Advisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdvisorRoleController extends RoleController implements Initializable {
    @FXML
    private TextField costField;

    @FXML
    private ComboBox<String> slotComboBox;

    @FXML
    private TextField traitField;

    @FXML
    private ListView<String> traitList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
    }
    private void initComboBox() {
        slotComboBox.getItems().removeAll(slotComboBox.getItems());
        slotComboBox.getItems().addAll("Political advisor", "High command", "Theorist",
                "Army chief", "Navy chief", "Air chief");
        slotComboBox.getSelectionModel().select("Political advisor");
    }

    private String getSelectedFromBox() {
        String result = slotComboBox.getValue();
        result = result.replace(" ", "_");
        return result.toLowerCase();
    }

    private void setSelectedFromBox(String slot) {
        slot.replace("_", " ");
        slot = slot.substring(0, 1).toUpperCase() + slot.substring(1);
        slotComboBox.getSelectionModel().select(slot);
    }

    @FXML
    void addTrait(ActionEvent event) {
        super.addTrait(traitList, traitField.getText());
    }

    @Override
    public String filename() {
        return "advisor-item.fxml";
    }

    public void fromAdvisor(Advisor advisor) {
        costField.setText(String.valueOf(advisor.getCost()));
        setSelectedFromBox(advisor.getSlot());
        traitList.getItems().addAll(advisor.getTraits());
    }
    public Advisor toAdvisor() {
        Advisor advisor = new Advisor();
        advisor.setCost(Integer.parseInt(costField.getText()));
        advisor.setSlot(getSelectedFromBox());
        advisor.setTraits(getTraits(traitList));
        advisor.toLedger();
        return advisor;
    }

}

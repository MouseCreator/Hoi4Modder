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

public class AdvisorRoleController extends RoleController<Advisor> implements Initializable {
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
        Advisor advisor = Advisor.createAdvisor();
        fromRole(advisor);
    }
    private void initComboBox() {
        slotComboBox.getItems().removeAll(slotComboBox.getItems());
        slotComboBox.getItems().addAll("Political advisor", "High command", "Theorist",
                "Army chief", "Navy chief", "Air chief");
        slotComboBox.getSelectionModel().select("Political advisor");
    }



    @FXML
    void addTrait(ActionEvent event) {
        super.addTrait(traitList, traitField.getText());
    }

    @Override
    public String filename() {
        return "advisor-item.fxml";
    }

    public void fromRole(Advisor advisor) {
        costField.setText(String.valueOf(advisor.getCost()));
        setSelectedFromBox(advisor.getSlot(), slotComboBox);
        traitList.getItems().addAll(advisor.getTraits());
    }
    public Advisor toRole() {
        Advisor advisor = new Advisor();
        advisor.setCost(Integer.parseInt(costField.getText()));
        advisor.setSlot(getSelectedFromBox(slotComboBox));
        advisor.setTraits(getTraits(traitList));
        advisor.toLedger();
        return advisor;
    }

}

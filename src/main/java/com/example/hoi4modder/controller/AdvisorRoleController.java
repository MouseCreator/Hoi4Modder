package com.example.hoi4modder.controller;

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

    @FXML
    void addTrait(ActionEvent event) {
        super.addTrait(traitList, traitField.getText());
    }

    @Override
    public String filename() {
        return "advisor-item.fxml";
    }
}

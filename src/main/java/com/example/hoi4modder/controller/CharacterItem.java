package com.example.hoi4modder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterItem implements Initializable {
    @FXML
    private CheckBox advisorBox;

    @FXML
    private Button autoSmallPortrait;

    @FXML
    private TextField characterIDField;

    @FXML
    private TextField characterNameField;

    @FXML
    private CheckBox countryLeaderBox;

    @FXML
    private CheckBox navyLeaderBox;

    @FXML
    private CheckBox unitLeaderBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.roles.CharacterRoles;
import com.example.hoi4modder.game.roles.CountryLeader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

/**
 * Controller for country leader
 */
public class CountryLeaderRoleController extends RoleController<CountryLeader> implements Initializable {

    @FXML
    private ComboBox<String> ideologyBox;

    @FXML
    private ListView<String> traitList;
    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TextField traitName;
    private CountryLeader countryLeader = CountryLeader.createCountryLeader();
    private final Map<String, List<String>> ideologies;

    /**
     * Creates role controller and initializes ideologies and types
     */
    public CountryLeaderRoleController() {
        this.ideologies = new HashMap<>();
        ideologies.put("Democratic", List.of("conservatism liberalism socialism".split(" ")));
        ideologies.put("Communism", List.of("marxism leninism stalinism anti_revisionism anarchist_communism".split(" ")));
        ideologies.put("Fascism", List.of("nazism gen_nazism fascism_ideology falangism rexism".split(" ")));
        ideologies.put("Neutrality", List.of("despotism oligarchism anarchism moderatism centrism".split(" ")));
    }

    /**
     * Adds trait to country leader
     */
    @FXML
    public void addTrait() {
        super.addTrait(traitList, traitName);
    }

    /**
     *
     * @return filename to load gui pane for country leader
     */
    @Override
    public String filename() {
        return "country-leader-item.fxml";
    }

    /**
     *
     * @param leader - country leader to create role from
     */
    @Override
    public void fromRole(CountryLeader leader) {
        String type = leader.getIdeology();
        for (String ideology : ideologies.keySet()) {
            if (ideologies.get(ideology).contains(type)) {
                ideologyBox.getSelectionModel().select(ideology);
                setTypesFromIdeology(ideology);
                String toSelect = stringToUpperCase(type);
                typeBox.getSelectionModel().select(toSelect);
            }
        }
        traitList.getItems().clear();
        traitList.getItems().addAll(leader.getTraits());
    }

    /**
     *
     * @return role, generated from interface inputs
     */
    @Override
    public CountryLeader toRole() {
       return countryLeader;
    }

    /**
     *
     * @return country leader role type
     */
    @Override
    public String getRoleType() {
        return CharacterRoles.COUNTRY_LEADER;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ideologyBox.getItems().clear();
        ideologyBox.getItems().addAll("Neutrality", "Democratic", "Fascism", "Communism");
        ideologyBox.getSelectionModel().select("Neutrality");
        setTypesFromIdeology("Neutrality");
        initializeContextMenu(traitList);
        typeBox.getSelectionModel().select(0);
        setValueListeners();
        initializeTextField(traitList, traitName);
        ideologyBox.show();
        ideologyBox.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case N, DIGIT1, NUMPAD1 -> { ideologyBox.getSelectionModel().select(0);
                    ideologyBox.hide(); }
                case D, DIGIT2, NUMPAD2 -> { ideologyBox.getSelectionModel().select(1);
                    ideologyBox.hide(); }
                case F, DIGIT3, NUMPAD3 -> { ideologyBox.getSelectionModel().select(2);
                    ideologyBox.hide(); }
                case C, DIGIT4, NUMPAD4 -> { ideologyBox.getSelectionModel().select(3);
                    ideologyBox.hide(); }
                default -> {
                }
            }
        });
    }

    private void setValueListeners() {
        typeBox.valueProperty().addListener((observableValue, old, newValue) -> {
            if (newValue != null)
                countryLeader.setIdeology(stringToLowerCase(newValue));
        });
    }

    private void setTypesFromIdeology(String ideology) {
        typeBox.getItems().clear();
        typeBox.getSelectionModel().clearSelection();
        List<String> list = ideologies.get(ideology);
        for(String str : list)
            typeBox.getItems().add(stringToUpperCase(str));
    }


    /**
     * Sets ideology type relevant for current ideology
     */
    @FXML
    void updateIdeologyType() {
        setTypesFromIdeology(ideologyBox.getValue());
        typeBox.getSelectionModel().select(0);
    }

    /**
     * Generates country leader role from character
     * @param character - input character
     */
    public void fromCharacter(GameCharacter character) {
        if (character.getRoles().containsKey(getRoleType())) {
            this.countryLeader = (CountryLeader) character.getRoles().get(getRoleType());
        } else {
            character.getRoles().put(getRoleType(), toRole());
        }
    }

    /**
     * Add trait to country leader
     */
    @FXML
    void removeTrait() {
        super.removeTrait(traitList);
    }
}

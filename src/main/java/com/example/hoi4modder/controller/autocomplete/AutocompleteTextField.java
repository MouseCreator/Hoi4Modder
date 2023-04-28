package com.example.hoi4modder.controller.autocomplete;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;

public class AutocompleteTextField {
    private final TextField textField;
    private final SortedSet<String> suggestions;
    private final ContextMenu suggestionsPopup;
    public AutocompleteTextField(TextField field, SortedSet<String> suggestions) {
        textField = field;
        this.suggestions = suggestions;
        suggestionsPopup = new ContextMenu();
        setListener();
    }

    public void clearSuggestions() {
        suggestions.clear();
    }
    public void addAllSuggestions(Collection<String> strCollection) {
        suggestions.addAll(strCollection);
    }

    private void setListener() {
        textField.textProperty().addListener((observableValue, s, t1) -> {
            String enteredValue = textField.getText();

            if (enteredValue == null || enteredValue.isEmpty()) {
                suggestionsPopup.hide();
                return;
            }
            List<String> filtered = suggestions.stream().filter(e -> e.toLowerCase().contains(enteredValue.toLowerCase()))
                    .toList();
            if (filtered.isEmpty()) {
                suggestionsPopup.hide();
                return;
            }
            showPopup(filtered, enteredValue);
            suggestionsPopup.show(textField, Side.BOTTOM, 0, 0);
        });
    }

    private void showPopup(List<String> possibleValues, String enteredValue) {
        List<CustomMenuItem> menuItems = new ArrayList<>();
        for (String suggestion : possibleValues) {
            final String res = suggestion;
            Label entryLabel = new Label();
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            item.setOnAction(actionEvent -> {
                textField.setText(res);
                textField.positionCaret(res.length());
                suggestionsPopup.hide();
            });
        }
        suggestionsPopup.getItems().clear();
        suggestionsPopup.getItems().addAll(menuItems);
    }
}

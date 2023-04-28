package com.example.hoi4modder.controller.autocomplete;

import javafx.geometry.Side;
import javafx.scene.control.*;

import java.util.*;

public class AutocompleteTextField {
    private final TextField textField;

    private final ScrollPane optionsScroll; // ?
    private final SortedSet<String> suggestions;
    private final ContextMenu suggestionsPopup;
    public AutocompleteTextField(TextField field, SortedSet<String> suggestions) {
        textField = field;
        this.suggestions = suggestions;
        optionsScroll = new ScrollPane();
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
            fillPopup(filtered);
            suggestionsPopup.show(textField, Side.BOTTOM, 0, 0);
        });
    }

    private void fillPopup(List<String> possibleValues) {
        List<CustomMenuItem> menuItems = new ArrayList<>();
        for (String suggestion : possibleValues) {
            final String res = suggestion;
            Label entryLabel = new Label();
            entryLabel.setText(suggestion);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                textField.setText(res);
                textField.positionCaret(res.length());
                suggestionsPopup.hide();
            });
            menuItems.add(item);


        }
        suggestionsPopup.getItems().clear();
        suggestionsPopup.getItems().addAll(menuItems);
    }
}

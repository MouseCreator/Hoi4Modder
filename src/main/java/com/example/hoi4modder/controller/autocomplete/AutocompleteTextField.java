package com.example.hoi4modder.controller.autocomplete;

import javafx.scene.control.TextField;

import java.util.Collection;
import java.util.SortedSet;

public class AutocompleteTextField extends TextField {
    private final SortedSet<String> suggestions;

    public AutocompleteTextField(SortedSet<String> suggestions) {
        this.suggestions = suggestions;
    }

    public void clearSuggestions() {
        suggestions.clear();
    }
    public void addAllSuggestions(Collection<String> strCollection) {
        suggestions.addAll(strCollection);
    }
}

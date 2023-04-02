package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.service.AbstractFactory;
import com.example.hoi4modder.service.Destinations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controller for character list
 */
public class CharacterListEditor extends ActivePaneController {

    @FXML
    private ListView<Pane> charactersListView;
    private final List<CharacterItemController> controllerList = new ArrayList<>();
    private String countryTag;
    @FXML
    private TextField tagTextField;
    @Override
    protected String getFilename() {
        return "character-list.fxml";
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @FXML
    public void duplicateSelected() {
        System.out.println("Duplicated");
    }

    @FXML
    public void addEmptyCharacter() {
        System.out.println("Added character");
    }
    @FXML
    public void removeSelected() {
        System.out.println("Removed character");
    }

    private GameCharacterList characters;

    @FXML
    public void loadCharactersByTag() {
        this.countryTag = tagTextField.getText();
        characters = loadListFromFile();
        createListOfCharacters();
    }

    private GameCharacterList loadListFromFile() {
        if (tagTextField.getText().isEmpty())
            return GameCharacterList.getArrayList();
        String tag = tagTextField.getText().toUpperCase();
        try {
            String filename = getFilename(tag);
            return AbstractFactory.get().getCharacterList(filename);
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error occurred during reading a file!", ButtonType.OK);
            alert.showAndWait();
        }
        return GameCharacterList.getArrayList();
    }

    private String getFilename(String tag) {
        FileSearchService searcher = (FileSearchService) parentController.getObjectPool().extract("filesearcher");
        searcher.setStrategy(new PutReplaceStrategy());
        searcher.setDirectory(Destinations.get().characters());
        String fileName = searcher.findCountryByTag(tag);
        parentController.getObjectPool().put("filesearcher", searcher);
        return fileName;
    }

    private void createListOfCharacters() {
        charactersListView.getItems().clear();
        for (GameCharacter character : characters) {
            try {
                loadItem(character);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        charactersListView.scrollTo(0);
    }
    private void loadItem(GameCharacter character) throws IOException {
        FXMLLoader itemLoader = new FXMLLoader();
        itemLoader.setLocation(getClass().getResource("character-item.fxml"));
        Pane pane = itemLoader.load();
        charactersListView.getItems().add(pane);
        CharacterItemController controller = itemLoader.getController();
        controller.setParent(this);
        controller.fromCharacter(character);
        controllerList.add(controller);
    }

    public String getCountryTag() {
        return countryTag;
    }

    public void setCountryTag(String countryTag) {
        this.countryTag = countryTag;
    }
}


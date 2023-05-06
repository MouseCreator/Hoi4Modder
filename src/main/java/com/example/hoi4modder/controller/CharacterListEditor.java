package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.autocomplete.AutocompleteTextField;
import com.example.hoi4modder.controller.multithreading.*;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.saver.CharacterSaver;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

/**
 * Controller for character list
 */
public class CharacterListEditor extends ActivePaneController implements Initializable {

    @FXML
    private ListView<Pane> charactersListView;
    private AutocompleteTextField searchAutocomplete;
    private final List<CharacterItemController> controllerList = new ArrayList<>();
    private FileWatcher fileWatcher;
    private String countryTag;
    private final GameCharacterList characters = GameCharacterList.getArrayList();
    @FXML
    private TextField tagTextField;
    @FXML
    private TextField searchTextField;
    @Override
    protected String getFilename() {
        return "character-list.fxml";
    }

    @Override
    public void save() {
        CharacterSaver saver = new CharacterSaver(this);
        saver.save();
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

    @FXML
    public void loadCharactersByTag() {
        this.countryTag = tagTextField.getText();
        loadListFromFile();
    }
    private String getFileToLoad(String tag) {
        FileSearchService searcher = parentController.getSavedData().fileSearchService();
        searcher.setStrategy(new PutReplaceStrategy());
        searcher.setDirectory(Destinations.get().characters());
        return searcher.findCountryByTag(tag);
    }

    private final Runnable onFileChanged = this::onFileExternalUpdate;
    private void loadListFromFile() {
        if (tagTextField.getText().isEmpty()) {
            GameCharacterList.getArrayList();
            return;
        }
        if (fileWatcher != null) {
            fileWatcher.stop();
        } else {
            fileWatcher = new FileWatcherPeriodic(onFileChanged, () -> getParent().getWindow().isFocused());
        }
        String tag = tagTextField.getText().toUpperCase();
        try {
            String filename = getFileToLoad(tag);
            fileWatcher.setFile(filename);
            fileWatcher.start();
            loadFromThread(filename);
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void loadFromThread(String filename) {
        EditorListTask task = new LoadingTask(this, filename, characters);
        Thread thread = new Thread(task);
        task.setOnSucceeded(workerStateEvent -> {
            controllerList.clear();
            controllerList.addAll(task.getControllers());
            loadItems(task.getPanes());
            addSearchSuggestions();
        });
        task.setOnFailed(workerStateEvent -> {
            resetAll();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred during loading characters!");
            alert.showAndWait();
        });
        thread.setName("CharacterLoadingThread");
        thread.start();
    }
    private SortedSet<String> characterIDs() {
        SortedSet<String> set = new TreeSet<>();
        for (GameCharacter character : characters) {
            set.add(character.getIdentification());
        }
        return set;
    }
    private void addSearchSuggestions() {
        if (searchAutocomplete == null) {
            searchAutocomplete = new AutocompleteTextField(searchTextField, characterIDs());
        } else {
            searchAutocomplete.clearSuggestions();
            searchAutocomplete.addAllSuggestions(characterIDs());
        }
    }

    private void loadItems(ObservableList<Pane> panes)  {
        charactersListView.setItems(panes);
        if (!charactersListView.getItems().isEmpty()) {
            charactersListView.scrollTo(0);
        }
    }
    public String getCountryTag() {
        return countryTag;
    }

    public void setCountryTag(String countryTag) {
        this.countryTag = countryTag;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        charactersListView.setFocusTraversable(false);
    }

    @FXML
    public void findCharacterByName() {
        EditorListTask task = new SearchingTask(this, searchTextField.getText(), characters);
        resetAll();
        Thread searchingThread = new Thread(task);
        task.setOnSucceeded(workerStateEvent -> {
            controllerList.addAll(task.getControllers());
            loadItems(task.getPanes());
        });
        task.setOnFailed(workerStateEvent -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred during searching characters!");
            alert.showAndWait();
        });
        searchingThread.setName("Searching-Thread");
        searchingThread.start();
    }
    private void resetAll() {
        controllerList.clear();
        charactersListView.getItems().clear();
    }

    public MainController getParent() {
        return this.parentController;
    }

    public void onFileExternalUpdate() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Current file has been changed. Update data in editor?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.YES) {
                loadCharactersByTag();
            }
        }
    }
    @Override
    public void onClose() {
        if (fileWatcher != null)
            fileWatcher.stop();
    }
}


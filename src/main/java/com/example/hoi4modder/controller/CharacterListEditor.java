package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.autocomplete.AutocompleteTextField;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.controller.character_extra.NoSelectionModel;
import com.example.hoi4modder.controller.multithreading.*;
import com.example.hoi4modder.controller.requests.CharacterEditorRequestHandler;
import com.example.hoi4modder.controller.requests.Request;
import com.example.hoi4modder.controller.requests.RequestHandler;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.common.Country;
import com.example.hoi4modder.game.common.DynamicCountry;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.SavedDataContainer;
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
public class CharacterListEditor extends ActivePaneController implements Initializable, ItemContainer<GameCharacter> {

    @FXML
    private ListView<Pane> charactersListView;
    private AutocompleteTextField searchAutocomplete;

    private final RequestHandler handler = new CharacterEditorRequestHandler(this);
    private boolean isLoaded = false;
    private final List<CharacterItemController> controllerList = new ArrayList<>();
    private FileWatcher fileWatcher;
    private final DynamicCountry country = new DynamicCountry();
    private final GameCharacterList characters = GameCharacterList.getArrayList();
    @FXML
    private TextField tagTextField;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button duplicateBtn;

    @FXML
    private Button newCharacterBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button saveBtn;
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
        setIsLoaded(false);
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @FXML
    public void duplicateSelected() {
        System.out.println("Duplicated");
    }

    @FXML
    public void addEmptyCharacter() {
        if (isNotLoaded()) return;
        GameCharacter newCharacter = GameCharacter.getSampleCharacter();
        GameCharacterCreator creator = new GameCharacterCreator(this, charactersListView.getItems(), controllerList);
        characters.add(newCharacter);
        creator.addItem(newCharacter);
        int last = charactersListView.getItems().size()-1;
        charactersListView.scrollTo(last);
    }

    private boolean isNotLoaded() {
        if (!isLoaded) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Country tag is not set");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    @FXML
    public void removeSelected() {
        System.out.println("Removed character");
    }

    @FXML
    public void loadCharactersByTag() {
        setIsLoaded(false);
        setCountryTag(tagTextField.getText());
        loadListFromFile();
    }
    private String getFileToLoad(String tag) {
        FileSearchService searcher = SavedDataContainer.get().fileSearchService();
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

        String tag = tagTextField.getText().toUpperCase();
        try {
            String filename = getFileToLoad(tag);
            loadFromThread(filename);
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
            onLoadingFailedAction();
        }
    }

    private void initFileWatcher() {
        if (fileWatcher != null) {
            fileWatcher.stop();
        } else {
            fileWatcher = new FileWatcherPeriodic(onFileChanged, () -> getParent().getWindow().isFocused());
        }
    }

    private void loadFromThread(String filename) {
        EditorListTask task = new LoadingTask(this, filename, characters);
        Thread thread = new Thread(task);
        task.setOnSucceeded(workerStateEvent -> onLoadingSuccessAction(filename, task));
        task.setOnFailed(workerStateEvent -> {
            onLoadingFailedAction();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred during loading characters!");
            alert.showAndWait();
        });
        thread.setName("CharacterLoadingThread");
        thread.start();
    }

    private void onLoadingSuccessAction(String filename, EditorListTask task) {
        controllerList.clear();
        controllerList.addAll(task.getControllers());
        loadItems(task.getPanes());
        addSearchSuggestions();
        setIsLoaded(true);
        initFileWatcher();
        fileWatcher.setFile(filename);
        fileWatcher.start();
    }

    private void setIsLoaded(boolean value) {
        this.isLoaded = value;
        boolean disabled = !value;
        duplicateBtn.setDisable(disabled);
        newCharacterBtn.setDisable(disabled);
        removeBtn.setDisable(disabled);
        saveBtn.setDisable(disabled);
    }

    private void onLoadingFailedAction() {
        resetAll();
        if (fileWatcher != null)
            fileWatcher.stop();

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
        return country.getTag();
    }

    public void setCountryTag(String countryTag) {
        this.country.setTag(countryTag);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        charactersListView.setFocusTraversable(false);
        charactersListView.setSelectionModel(new NoSelectionModel<>());
    }

    @FXML
    public void findCharacterByName() {
        findCharacters(searchTextField.getText());
    }

    public void findCharacters(String target) {
        EditorListTask task = new SearchingTask(this, target, characters);
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


    @Override
    public void associateItem(ListItemController<GameCharacter> item) {
        item.setParent(this);
    }

    @Override
    public void handle(Request request) {
        handler.onRequest(request);
    }

    @Override
    public MainController getMainController() {
        return parentController;
    }

    public GameCharacterList getCharacters() {
        return characters;
    }

    public ListView<Pane> getItems() {
        return charactersListView;
    }

    public List<CharacterItemController> getControllers() {
        return controllerList;
    }
}


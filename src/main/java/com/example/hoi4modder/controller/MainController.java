package com.example.hoi4modder.controller;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.FileSearcher;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.model.files.manager.strategy.PutStrategy;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ObjectPool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane leftContent;

    @FXML
    private Pane mainContent;

    @FXML
    private MenuBar mainMenu;

    @FXML
    private TextArea consoleView;

    @FXML
    private ScrollPane mainPane;

    @FXML
    private Pane rightContent;

    @FXML
    private ScrollPane sideButton;

    @FXML
    private ScrollPane sideTop;

    private ActivePaneController currentActive;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadPage(new CharacterListEditor().load(this));
    }
    private void loadScene() {
        mainContent.getChildren().clear();
        mainContent.getChildren().add(currentActive.getContent());
    }

    /**
     * Changes workspace pane to new page
     * @param toLoad - page to open. Must be initialized.
     */
    public void loadPage(ActivePaneController toLoad) {
        if (closeActiveController())
            loadNext(toLoad);

    }

    private boolean closeActiveController() {
        if (currentActive == null) {
            return true;
        }
        return(currentActive.onCloseRequest());
    }

    private void loadNext(ActivePaneController key) {
        if (currentActive != null)
            currentActive.onClose();
        currentActive = key;
        loadScene();
        currentActive.initContent();
    }
    public void report(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: red");
    }
    public void inform(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: black");
    }
    public void warn(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: orange");
    }
    public void congratulate(String text) {
        this.consoleView.setText(text);
        this.consoleView.setStyle("-fx-text-fill: green");
    }

    private final ObjectPool objectPool;

    public MainController() {
        objectPool = ObjectPool.getHashObjectPool();
        objectPool.put("filesearcher", new FileSearchService());
    }
    public ObjectPool getObjectPool() {
        return objectPool;
    }
    private LoadedData getLoadedData() {
        LoadedData loadedData = new LoadedData();
        //loadedData.setGraphicsData();
        return loadedData;
    }

    private void loadGraphicsData() {
        FileSearchService searcher = (FileSearchService) objectPool.get("searcher");
        searcher.setDirectory(Destinations.get().interfaceDir());
        searcher.setStrategy(new PutStrategy());
        String filename = searcher.findInstance("leader");
    }
}

package com.example.hoi4modder.controller;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutStrategy;
import com.example.hoi4modder.model.files.maps.DataMap;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.AbstractFactory;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ObjectPool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
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
        objectPool.put("data", getLoadedData());
    }
    public ObjectPool getObjectPool() {
        return objectPool;
    }
    private LoadedData getLoadedData() {
        LoadedData loadedData = new LoadedData();
        loadedData.setGraphicsData(loadGraphicsData());
        loadedData.setLocalisationData(loadLocalisationData());
        return loadedData;
    }
    private DataPool<String> loadLocalisationData() {
        DataPool<String> localisationData = DataPool.getHashStringPool();
        FileSearchService searcher = (FileSearchService) objectPool.extract("filesearcher");
        searcher.setDirectory(Destinations.get().localisation());
        searcher.setStrategy(new PutStrategy());
        String[] keywords = new String[] {"characters"};
        for(String s : keywords) {
            DataMap<String> map = createLocaleMap(searcher, s);
            if (map != null) {
                localisationData.addDataMap(s, map);
            }
        }
        objectPool.put("filesearcher", searcher);
        return localisationData;
    }
    private DataPool<String> loadGraphicsData() {
        DataPool<String> graphicData = DataPool.getHashStringPool();
        FileSearchService searcher = (FileSearchService) objectPool.extract("filesearcher");
        searcher.setDirectory(Destinations.get().interfaceDir());
        searcher.setStrategy(new PutStrategy());
        String[] keywords = new String[] {"leader", "ideas_characters"};
        for(String s : keywords) {
            DataMap<String> map = createGraphicsMap(searcher, s);
            if (map != null) {
                graphicData.addDataMap(s, map);
            }
        }
        objectPool.put("filesearcher", searcher);
        return graphicData;
    }

    private DataMap<String> createGraphicsMap(FileSearchService searcher, String keyword) {
        try {
            String filename = searcher.findInstance(keyword);
            return AbstractFactory.get().graphicsMap(filename);
        } catch (Exception e) {
            return null;
        }
    }

    private DataMap<String> createLocaleMap(FileSearchService searcher, String keyword) {
        try {
            String filename = searcher.findInstance(keyword);
            return AbstractFactory.get().localeMap(filename);
        } catch (Exception e) {
            return null;
        }
    }
}

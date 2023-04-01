package com.example.hoi4modder.model.files.maps;
public class LoadedData {
    public void setLocalisationData(DataPool<String> localisationData) {
        this.localisationData = localisationData;
    }

    private DataPool<String> localisationData = DataPool.getHashStringPool();

    public void setGraphicsData(DataPool<String> graphicsData) {
        this.graphicsData = graphicsData;
    }

    private DataPool<String> graphicsData = DataPool.getHashStringPool();

    public DataPool<String> getGraphicsData() {
        return graphicsData;
    }
    public DataPool<String> getLocalisationData() {
        return localisationData;
    }
}

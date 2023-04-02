package com.example.hoi4modder.model.files.maps;

/**
 * Class that stores data from game and modification
 */
public class LoadedData {
    public void setLocalisationData(DataPool<String> localisationData) {
        this.localisationData = localisationData;
    }

    private DataPool<String> localisationData = DataPool.getHashStringPool();

    public void setGraphicsData(DataPool<String> graphicsData) {
        this.graphicsData = graphicsData;
    }

    private DataPool<String> graphicsData = DataPool.getHashStringPool();

    /**
     *
     * @return map of graphics objects keys and destinations
     */
    public DataPool<String> getGraphicsData() {
        return graphicsData;
    }

    /**
     *
     * @return map of keys and localisations
     */
    public DataPool<String> getLocalisationData() {
        return localisationData;
    }
}

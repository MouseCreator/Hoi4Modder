package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutStrategy;
import com.example.hoi4modder.service.AbstractFactory;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ObjectPool;

public class GraphicsBuilder implements DataPoolBuilder<String> {

    private final DataPool<String> dataPool = DataPool.getHashStringPool();
    @Override
    public void loadData(ObjectPool objectPool) {
        FileSearchService searcher = (FileSearchService) objectPool.extract("filesearcher");
        searcher.setDirectory(Destinations.get().interfaceDir());
        searcher.setStrategy(new PutStrategy());
        String[] keywords = new String[] {"leader", "ideas_characters"};
        for(String s : keywords) {
            DataMap<String> map = createGraphicsMap(searcher, s);
            if (map != null) {
                dataPool.addDataMap(s, map);
            }
        }
        objectPool.put("filesearcher", searcher);
    }
    private DataMap<String> createGraphicsMap(FileSearchService searcher, String keyword) {
        try {
            String filename = searcher.findInstance(keyword);
            return AbstractFactory.get().graphicsMap(filename);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public DataPool<String> getResult() {
        return dataPool;
    }
}

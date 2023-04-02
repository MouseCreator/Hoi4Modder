package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutStrategy;
import com.example.hoi4modder.service.AbstractFactory;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.ObjectPool;

public class LocalisationBuilder implements DataPoolBuilder<String>{
    private final DataPool<String> localisationData = DataPool.getHashStringPool();
    @Override
    public void loadData(ObjectPool objectPool) {
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

    }
    @Override
    public DataPool<String> getResult() {
        return localisationData;
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

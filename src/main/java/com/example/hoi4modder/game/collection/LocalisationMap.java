package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.model.files.iovisitor.Visitor;
import com.example.hoi4modder.model.files.maps.DataMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalisationMap implements Visitable{
    private final Map<String, String> map = new HashMap<>();

    private String filename;

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    public String put(String key, String value) {
       return map.put(key, value);
    }
    public String get(String key) {
        return map.get(key);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public DataMap<String> toDataMap() {
        return new DataMap<>(map);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitLocalisationMap(this);
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }
}

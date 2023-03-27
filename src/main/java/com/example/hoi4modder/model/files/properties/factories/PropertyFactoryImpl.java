package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

import java.util.Queue;

public class PropertyFactoryImpl implements PropertyFactory{
    private final BlockPropertyFactory firstInChain = new BlockPropertyFactory();
    @Override
    public Property toProperty(String origin) {
        return firstInChain.createProperty(origin);
    }

    @Override
    public Property toBlock(Queue<String> origin) {
        Property property = firstInChain.createProperty(origin.poll());
        while (!origin.isEmpty()) {
            String current = origin.poll();
            if (current.startsWith("}")) {
                break;
            }
            property.add(firstInChain.toProperty(current));
        }
        return property;
    }

}

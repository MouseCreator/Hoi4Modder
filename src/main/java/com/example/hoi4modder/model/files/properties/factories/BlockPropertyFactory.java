package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class BlockPropertyFactory extends PropertyFactoryMethod {
    private final BlockProperty property = new BlockProperty();
    private final LocalisationBlockFactory nextInChain = new LocalisationBlockFactory();
    @Override
    public ChainedFactory next() {
        return nextInChain;
    }
    @Override
    public Property toProperty(String origin) {
        BlockProperty result = new BlockProperty();
        String[] s = split(origin);
        result.setKey(origin.substring(0, origin.indexOf("=")));
        PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
        origin = s[1];
        while (!origin.startsWith("}")) {

            String[] parts = split(origin);
            if(parts[0].endsWith("={")) {
                result.add(propertyFactory.toProperty(origin));
                origin = skip(parts[1]);
            } else {
                result.add(propertyFactory.toProperty(parts[0]));
                origin = parts[1];
            }
        }
        return result;
    }

    private String skip(String origin) {
        boolean ignore = false;
        int bracketCount = 1;
        int charNum = 0;
        for (char ch : origin.toCharArray()) {
            charNum++;
            if (ch == '"') {
                ignore = !ignore;
            }
            if (!ignore) {
                if (ch == '{') {
                    bracketCount++;
                } else if (ch == '}') {
                    bracketCount--;
                    if (bracketCount == 0)
                        return origin.substring(charNum);
                }
            }
        }
        throw new IllegalStateException("Unable to clo bracket!");
    }
    @Override
    protected Property getProperty() {
        return property;
    }

    @Override
    public boolean canHandle(String origin) {
        return origin.contains("=") && origin.contains("{");
    }
}

package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.Country;
import com.example.hoi4modder.model.files.properties.SavedElement;
import com.example.hoi4modder.model.files.service.FileServiceImpl;
import com.example.hoi4modder.service.Destinations;

public class Loader implements Visitor {

    @Override
    public void visitCountry(Country country) {

    }

    private void loadCountry(Country country) {
        FileServiceImpl fileService = new FileServiceImpl();
        SavedElement structure = fileService.read(Destinations.get().countryFile(country));
        //FieldInjection.getInjector(Country.class, country);
    }
}

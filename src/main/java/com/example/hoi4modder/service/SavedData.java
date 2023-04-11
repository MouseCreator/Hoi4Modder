package com.example.hoi4modder.service;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.maps.LoadedData;

/**
 * was: OBJECT POOL PATTERN?
 * Might be reused to create multiple file searchers
 */
public record SavedData(FileSearchService fileSearchService, LoadedData loadedData) {
}

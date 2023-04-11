package com.example.hoi4modder.model.files.manager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * OBJECT POOL for File Searchers
 */
public class FileSearcherPool {
    private final BlockingQueue<FileSearchService> fileSearchers;
    private final int capacity;
    private int remaining;
    FileSearcherPool(BlockingQueue<FileSearchService> queue, int capacity) {
        this.fileSearchers = queue;
        this.capacity = capacity;
        this.remaining = capacity;
    }

    public static FileSearcherPool arrayFileSearcherPool(int capacity) {
        return new FileSearcherPool(new ArrayBlockingQueue<>(capacity), capacity);
    }
    public static FileSearcherPool linkedFileSearcherPool(int capacity) {
        return new FileSearcherPool(new LinkedBlockingQueue<>(capacity), capacity);
    }
    public void fill() {
        for (; remaining < capacity; remaining++) {
            put(new FileSearchService());
        }
    }
    public FileSearchService take() {
        try {
            return this.fileSearchers.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void put(FileSearchService fileSearchService) {
        this.fileSearchers.add(fileSearchService);
    }
}

package com.example.hoi4modder.controller.character_extra;

import java.util.LinkedList;
import java.util.List;

public class CharacterInfo {
    private final List<Integer> positions = new LinkedList<>();

    public void insertPosition(int i) {
        positions.add(i);
        positions.sort(Integer::compareTo);
    }
    public int getAndInsertPosition(int target) {
        int result = 0;
        for (int i : positions) {
            if (i > target) {
                insertPosition(target);
                return result;
            }
            result++;
        }
        insertPosition(target);
        return positions.size()-1;
    }

    public void removePosition(int targetIndex) {
        positions.remove((Integer) targetIndex);
    }
}

package com.example.hoi4modder.controller.character_extra;

import java.util.LinkedList;
import java.util.List;

public class CharacterInfo {
    private final List<Integer> positions = new LinkedList<>();
    public int getAndInsertPosition(int target) {
        int result = 0;
        for (int i : positions) {
            if (i > target) {
                positions.add(result, target);
                return result;
            }
            result++;
        }
        positions.add(target);
        return result;
    }

    public void removePosition(int targetIndex) {
        positions.remove((Integer) targetIndex);
    }
}

package com.example.hoi4modder.controller.character_extra;

import java.util.LinkedList;
import java.util.List;

/**
 * Additional info about character, such as roles that has been added
 */
public class CharacterInfo {
    private final List<Integer> positions = new LinkedList<>();

    /**
     *
     * @param target - target position of the role
     * @return position to insert new role
     */
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

    /**
     * Clears role position
     * @param targetIndex - target position of the role
     */
    public void removePosition(int targetIndex) {
        positions.remove((Integer) targetIndex);
    }
}

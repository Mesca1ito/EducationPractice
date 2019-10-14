package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.Comparator;
import java.util.List;

public class StackOfBoxes {
    int createStack(List<Box> boxes) {
        boxes.sort(new BoxComparator());
        int maxHeight = 0;
        int[] stackMap = new int[boxes.size()];
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i, stackMap);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight;
    }

    int createStack(List<Box> boxes, int bottomIndex, int[] stackMap) {
        if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
            return stackMap[bottomIndex];
        }

        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack(boxes, i, stackMap);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        maxHeight += bottom.height;
        stackMap[bottomIndex] = maxHeight;
        return maxHeight;
    }


    int createStack(List<Box> boxes, Box bottom, int offset, int[] stackMap) {
        if (offset >= boxes.size()) return 0;
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = createStack(boxes, newBottom, offset + 1, stackMap);
                stackMap[offset] += newBottom.height;
            }
            heightWithBottom = stackMap[offset];
        }

        int heightWithoutBottom = createStack(boxes, bottom, offset + 1, stackMap);

        return Math.max(heightWithBottom, heightWithoutBottom);
    }
}

class Box {
    int height;
    int width;
    int depth;

    boolean canBeAbove(Box box) {
        return height + width + depth < box.height + box.width + box.depth;
    }
}

class BoxComparator implements Comparator<Box> {

    @Override
    public int compare(Box o1, Box o2) {
        return o2.height - o1.height;
    }
}
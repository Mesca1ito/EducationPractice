package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

public class MagicIndex {
    public static int magicFast(int[] array) {
        return magicFast(array, 0, array.length - 1);
    }

    // If distinct
    private static int magicFast(int[] array, int start, int end) {
        if (end < start) return -1;
        int midIndex = (start + end) / 2;
        if (array[midIndex] == midIndex) return midIndex;
        if (array[midIndex] < midIndex) return magicFast(array, midIndex + 1, end);
        else return magicFast(array, start, midIndex - 1);
    }


    int magicFastIfNotDistinct(int[] array, int start, int end) {
        if (start < end) return -1;

        int midIndex = (start + end) / 2;
        int midValue = array[midIndex];
        if (midValue == midIndex) return midIndex;

        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicFastIfNotDistinct(array, start, leftIndex);
        if (left != -1) return left;

        int rightIndex = Math.max(midIndex + 1, midValue);
        return magicFastIfNotDistinct(array, rightIndex, end);
    }
}

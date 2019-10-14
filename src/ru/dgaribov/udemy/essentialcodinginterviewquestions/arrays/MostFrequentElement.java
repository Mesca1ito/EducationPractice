package ru.dgaribov.udemy.essentialcodinginterviewquestions.arrays;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentElement {
    static Integer findMostFrequent(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Map<Integer, Integer> frequencies = new HashMap<>();
        int maxFreq = 0;
        int mostFreq = -1;
        for (int element: arr) {
            Integer frequency = frequencies.get(element);
            if (frequency == null) frequency = 0;
            frequencies.put(element, ++frequency);

            if (frequency > maxFreq) {
                maxFreq = frequency;
                mostFreq = element;
            }
        }

        return mostFreq;
    }

    public static void main(String[] args) {
        System.out.println(findMostFrequent(new int[]{1, 3, 1, 3, 2 ,1, 3, 3, 3}));
    }
}

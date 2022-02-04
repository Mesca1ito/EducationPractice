package ru.dgaribov.microsoft.assesment20211201.task3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(solution("BAOOLLNNOLOLGBAX"));
    }

    public static int solution(String S) {
        final String BALLOON = "BALLOON";
        if (S == null || S.length() < BALLOON.length()) return 0;
        // write your code in Java SE 8

        Map<Character, Integer> sCharMap = buildCharMap(S);
        Map<Character, Integer> balloonCharMap = buildCharMap(BALLOON);

        return getNumberOfBalloons(sCharMap, balloonCharMap);
    }

    /**
     *
     * Iteratively substracting balloon chars from the S chars. if full circle ends and number of chars is sufficient,
     * increase result, otherwise return curremt result
     *
     * @param strCharMap
     * @param balloonCharMap
     * @return
     */
    private static int getNumberOfBalloons(Map<Character, Integer> strCharMap, Map<Character, Integer> balloonCharMap) {
        int result = 0;
        while (true) {
            for (Map.Entry<Character, Integer> entry: balloonCharMap.entrySet()) {
                if (!strCharMap.containsKey(entry.getKey()) || strCharMap.get(entry.getKey()) < entry.getValue()) {
                    return result;
                }
                strCharMap.put(entry.getKey(), strCharMap.get(entry.getKey()) - entry.getValue());
            }
            result++;
        }

    }

    private static Map<Character, Integer> buildCharMap(String s) {
        Map<Character, Integer> charsMap = new HashMap<>();

        for (char ch: s.toCharArray()) {
            if (!charsMap.containsKey(ch)) {
                charsMap.put(ch, 1);
            } else {
                int val = charsMap.get(ch);
                charsMap.put(ch, val + 1);
            }
        }

        return charsMap;
    }
}

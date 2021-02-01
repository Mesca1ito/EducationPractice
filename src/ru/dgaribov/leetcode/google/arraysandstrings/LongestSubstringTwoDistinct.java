package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * @author David Garibov
 */
public class LongestSubstringTwoDistinct {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abc"));
    }
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> windowPositions = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length()) {
            windowPositions.put(s.charAt(right), right);
            if (windowPositions.size() == 3) {
                int leftMost = windowPositions.values().stream().min(Integer::compareTo).get();
                windowPositions.remove(s.charAt(leftMost));
                left = leftMost + 1;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }
}

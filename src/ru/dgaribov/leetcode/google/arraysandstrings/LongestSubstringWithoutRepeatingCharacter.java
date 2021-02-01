package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("dvdf"));
    }

    public static int lengthOfLongestSubstring(String s) {

        int cur = 0;
        int max = 0;

        Map<Character, Integer> positions = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!positions.containsKey(s.charAt(i))) {
                positions.put(s.charAt(i), i);
                max = Math.max(max, ++cur);
            } else {
                i = positions.get(s.charAt(i));
                positions = new HashMap<>();
                cur = 0;
            }
        }

        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int right = 0, left = 0; right < n; right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(map.get(s.charAt(right)) + 1, left);
            }
            ans = Math.max(ans, right - left + 1);
            map.put(s.charAt(right), right);
        }
        return ans;
    }
}

package ru.dgaribov.facebook.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class MinLengthSubString {
    // Add any helper functions you may need here
    Map<Character, Integer> buildCharMap(String str) {
        Map<Character, Integer> result = new HashMap<>();
        for (char ch : str.toCharArray()) {
            result.merge(ch, 1, Integer::sum);
        }
        return result;
    }

    boolean charsIncluded(Map<Character, Integer> bigMap, Map<Character, Integer> smallMap) {
        for (char ch : smallMap.keySet()) {
            if (!bigMap.containsKey(ch) || smallMap.get(ch) > bigMap.get(ch)) return false;
        }

        return true;
    }

    int minLengthSubstring(String s, String t) {
        // Write your code here
        if (s.equals(t)) return t.length();
        int i = 0;
        int j = 0;

        Map<Character, Integer> curMap = new HashMap<>();
        Map<Character, Integer> smallMap = buildCharMap(t);

        boolean charsIncluded = false;
        while (j < s.length() && !(charsIncluded = charsIncluded(curMap, smallMap))) {
            curMap.merge(s.charAt(j++), 1, Integer::sum);
        }

        if (!charsIncluded) return -1;

        int minWindow = j - i;

        while (j < s.length()) {
            char curChar = s.charAt(i);
            if (curMap.get(curChar) > smallMap.getOrDefault(curChar, 0)) {
                curMap.put(curChar, Math.max(0, curMap.get(curChar) - 1));
                i++;
                minWindow = Math.min(minWindow, j - i + 1);
            } else {
                if (j + 1 < s.length()) {
                    curMap.merge(s.charAt(j + 1), 1, Integer::sum);
                }
                j++;
            }
        }

        return minWindow;
    }

    public static void main(String[] args) {
        MinLengthSubString minLengthSubString = new MinLengthSubString();
        int result = minLengthSubString.minLengthSubstring("dcbefebce", "fd");
        System.out.println(result);
    }
}

package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        MinimumWindowSubstring app = new MinimumWindowSubstring();
        System.out.println(app.minWindow("a", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s.equals(t)) return t;
        int i = 0;
        int j = 0;
        Map<Character, Integer> smallMap = buildCharMap(t);
        Map<Character, Integer> curMap = new HashMap<>();
        boolean charsIncluded = false;
        while (j < s.length() && !(charsIncluded = charsIncluded(curMap, smallMap))) {
            curMap.merge(s.charAt(j), 1, Integer::sum);
            j++;
        }
        if (!charsIncluded) return "";

        int minLength = j - i + 1;
        String result = "";
        while (j < s.length()) {
            char curChar = s.charAt(i);
            int newCountForCurChar = Math.max(0, curMap.get(curChar) - 1);
            if (newCountForCurChar >= smallMap.getOrDefault(curChar, 0)) {
                curMap.put(curChar, newCountForCurChar);
                i++;
            } else {
                if (j + 1 < s.length()) {
                    curMap.merge(s.charAt(j + 1), 1, Integer::sum);
                }
                j++;
            }
            int curLength = j - i + 1;
            if (curLength < minLength) {
                minLength = curLength;
                result = s.substring(i, j + 1);
            }
        }

        return result;
    }

    Map<Character, Integer> buildCharMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.merge(ch, 1, Integer::sum);
        }

        return map;
    }

    boolean charsIncluded(Map<Character, Integer> big, Map<Character, Integer> small) {
        for (char ch : small.keySet()) {
            if (!big.containsKey(ch) || small.get(ch) > big.get(ch)) return false;
        }
        return true;
    }
}

package ru.dgaribov.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * @author David Garibov
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            int[] charMap = new int[26];
            for (char ch: str.toCharArray()) {
                charMap[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int num: charMap) {
                sb.append("#").append(num);
            }
            List<String> anagrams = map.computeIfAbsent(sb.toString(), key -> new ArrayList<>());
            anagrams.add(str);
        }

        return new ArrayList<>(map.values());
    }
}

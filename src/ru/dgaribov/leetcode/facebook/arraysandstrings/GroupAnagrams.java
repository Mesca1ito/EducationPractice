package ru.dgaribov.leetcode.facebook.arraysandstrings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author David Garibov
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams app = new GroupAnagrams();
        List<List<String>> result = app.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        System.out.println(result);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String hash = buildHash(str);
            List<String> list = map.computeIfAbsent(hash, key -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }


    private String buildHash(String str) {
        int[] counts = new int[26];
        for(char ch: str.toCharArray()) {
            counts[ch - 'a']++;
        }

        return Arrays.stream(counts).mapToObj(i -> "" + i ).collect(Collectors.joining("#"));
    }
}

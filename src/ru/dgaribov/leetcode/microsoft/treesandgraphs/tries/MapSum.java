package ru.dgaribov.leetcode.microsoft.treesandgraphs.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 *
 * @author David Garibov
 */
public class MapSum {
    private Map<String, Integer> map;
    private TrieNode2 root;

    public MapSum() {
        map = new HashMap<>();
        root = new TrieNode2();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode2 node = root;
        for (char c: key.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode2());
            }
            node = node.children.get(c);
            node.score += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode2 node = root;
        int sum = 0;
        for (char ch: prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return 0;
            }
        }
        return node.score;
    }
}

class TrieNode2 {
    Map<Character, TrieNode2> children = new HashMap<>();
    int score;
}

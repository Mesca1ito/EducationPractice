package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationsWithDups {


    public static void main(String[] args) {
        PermutationsWithDups app = new PermutationsWithDups();
        List<String> perms = app.printPerms("aaa");
        System.out.println(perms);
    }


    private List<String> printPerms(String s) {
        Map<Character, Integer> freqTable = buildFreqTable(s);
        List<String> result = new ArrayList<>();
        printPerms(freqTable, "", s.length(), result);
        return result;
    }


    private Map<Character, Integer> buildFreqTable(String s) {
        Map<Character, Integer> freqTable = new HashMap<>();
        for (Character character : s.toCharArray()) {
            freqTable.merge(character, 1, (a, b) -> a + b);
        }
        return freqTable;
    }

    private void printPerms(Map<Character, Integer> map, String prefix, int remaining, List<String> result) {
        if (remaining == 0) {
            result.add(prefix);
            return;
        }
        for (char ch: map.keySet()) {

            int count = map.get(ch);
            if (count <= 0) continue;

            map.put(ch, count - 1);
            printPerms(map, prefix + ch, remaining - 1, result);
            map.put(ch, count);
        }

    }
}

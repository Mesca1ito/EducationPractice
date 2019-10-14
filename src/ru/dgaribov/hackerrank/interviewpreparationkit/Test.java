package ru.dgaribov.hackerrank.interviewpreparationkit;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        for (int i = 97; i <= 122; i++) {
            System.out.println((char) i + " " + (i - 97));
        }

        Map<Character, Integer> charMap = new HashMap<>();
        boolean excentric = false;
        List<Map.Entry<Character, Integer>>  sortedEntries = charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());



    }
}

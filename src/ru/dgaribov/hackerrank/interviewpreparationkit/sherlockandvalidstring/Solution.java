package ru.dgaribov.hackerrank.interviewpreparationkit.sherlockandvalidstring;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        char[] charArr = s.toCharArray();
        boolean twoTimesChar = false;
        Map<Character, Integer> charMap = new HashMap<>();
        for (char theChar: charArr) {
            int occursNumber = charMap.getOrDefault(theChar, 0);
            charMap.put(theChar, ++occursNumber);
        }

        boolean thereWasAGap = false;
        List<Map.Entry<Character, Integer>>  sortedEntries = charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        for (int i = 0; i < sortedEntries.size() - 1; i++) {
            if (!sortedEntries.get(i).getValue().equals(sortedEntries.get(i+1).getValue())) {
                if (i == 0) {
                    thereWasAGap = true;
                    continue;
                }
                if (i + 1 == sortedEntries.size() - 1) {
                    if (thereWasAGap) return "NO";
                    if (sortedEntries.get(i+1).getValue() - sortedEntries.get(i).getValue() > 1) return "NO";
                    continue;
                }

                return "NO";

            }
        }

        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        System.out.println(result);

        scanner.close();
    }
}
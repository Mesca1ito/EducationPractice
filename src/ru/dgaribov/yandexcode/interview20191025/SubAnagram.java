package ru.dgaribov.yandexcode.interview20191025;

import java.util.HashMap;
import java.util.Map;

public class SubAnagram {

    public static void main(String[] args) {
        System.out.println(findAnagram("abgaegza", "ggae"));
    }

    private static int findAnagram(String s1, String s2) {
        String bigOne = getBigOne(s1, s2);
        String smallOne = getBigOne(s1, s2);
        int currentMatch = 0;
        Map<Character, Integer> smallMap = buildMap(smallOne);
        Map<Character, Integer> currentMap = new HashMap<>();

        for (int i = 0; i < bigOne.length(); i++) {
            char currentChar = bigOne.charAt(i);
            currentMap.merge(currentChar, 1, (k, v) -> (v + 1));
            if (currentMap.get(currentChar).equals(smallMap.get(currentChar))) currentMatch++;
            else if (currentMatch > 0) currentMatch--;
            if (i + 1 > smallOne.length()) {
                int throwAway = i - smallOne.length() - 1;
                currentMap.merge(bigOne.charAt(throwAway), 0, (k, v) -> (v - 1));
                if (currentMap.get(bigOne.charAt(throwAway)).equals(smallMap.get(bigOne.charAt(throwAway))))
                    currentMatch++;
                else if (currentMatch > 0) currentMatch--;

            }

            if (currentMatch == smallMap.size()) {
                return i - smallOne.length();
            }
        }

        return -1;

    }

    private static String getBigOne(String s1, String s2) {
        return s1.length() >= s2.length() ? s1 : s2;
    }

    private static String getSmallOne(String s1, String s2) {
        return s1.length() < s2.length() ? s1 : s2;
    }


    private static Map<Character, Integer> buildMap(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (char ch : s.toCharArray()) {
            result.merge(ch, 1, (k, v) -> (v + 1));
        }

        return result;
    }

}

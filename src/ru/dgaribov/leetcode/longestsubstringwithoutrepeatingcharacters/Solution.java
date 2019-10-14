package ru.dgaribov.leetcode.longestsubstringwithoutrepeatingcharacters;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int biggestSubstringLength = 0;

        char[] charArr = s.toCharArray();

        List<Character> subStr = new ArrayList<>();

        for (int i = 0; i < charArr.length; i++) {
            for (int k = i; k < charArr.length; k++) {
                if (subStr.indexOf(charArr[k]) == -1) {
                    subStr.add(charArr[k]);
                    if (subStr.size() > biggestSubstringLength) {
                        biggestSubstringLength = subStr.size();
                    }
                } else {
                    subStr.clear();
                    break;
                }
            }
        }

        return biggestSubstringLength;
    }

    private static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] charArr = s.toCharArray();
        boolean[] charFlag = new boolean[256];

        int biggestSS = 0;

        for (int i = 0, k = 0; k < charArr.length; ) {
            if (!charFlag[charArr[k]]) {
                int distance = k - i + 1;
                if (distance > biggestSS) {
                    biggestSS = distance;
                }
                charFlag[charArr[k++]] = true;

            } else {
                charFlag[charArr[i++]] = false;
            }
        }

        return biggestSS;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("jbpnbwwd"));
    }
}

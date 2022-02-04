package ru.dgaribov.leetcode.microsoft.backtracking;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * @author David Garibov
 */
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        if (p.equals(s)) return true;
        if (p.length() > 0 && p.chars().allMatch(c -> c == '*')) return true;
        if (p.isEmpty() || s.isEmpty()) return false;


        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx)) {
                ++sIdx;
                ++pIdx;
            // If pattern character = '*'
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            } else if (starIdx == -1) {
                return false;
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            } else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(pIdx) != '*') {
                return false;
            }
        }

        return true;
    }
}

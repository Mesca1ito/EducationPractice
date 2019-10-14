package ru.dgaribov.leetcode.longestpalindromicstring;

public class Solution {
    public static String longestPalindrome(String s) {

        if (s.length() == 1 || s.length() == 0) return s;

        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            String palindrome = "";
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                palindrome = findPalindrome(i, i + 1, s);
                if (palindrome.length() > longestPalindrome.length()) {
                    longestPalindrome = palindrome;
                }
            }

            if (i + 2 < s.length() && s.charAt(i) == s.charAt(i + 2)) {
                palindrome = findPalindrome(i, i + 2, s);
                if (palindrome.length() > longestPalindrome.length()) {
                    longestPalindrome = palindrome;
                }
            }
        }

        if (longestPalindrome.equals("")) return s.substring(0,1);

        return longestPalindrome;
    }

    private static String findPalindrome(int start, int end, String string) {
        if (start < 0 ||
        end >= string.length() ||
        string.charAt(start) != string.charAt(end)) {
            return string.substring(start + 1, end);
        }

        return findPalindrome(start -1, end + 1, string);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }
}

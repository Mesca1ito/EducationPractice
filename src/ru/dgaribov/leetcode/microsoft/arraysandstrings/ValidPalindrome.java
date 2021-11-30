package ru.dgaribov.leetcode.microsoft.arraysandstrings;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * @author David Garibov
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (invalidChar(s.charAt(left))) {left++; continue;}
            if (invalidChar(s.charAt(right))) {right--; continue;}

            if (Character.toUpperCase(s.charAt(left)) != Character.toUpperCase(s.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    private static boolean invalidChar(char ch) {
        return !((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }

    public static void main(String[] args) {
        boolean result = isPalindrome("race a car");
        System.out.println(result);
    }
}

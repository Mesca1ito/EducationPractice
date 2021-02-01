package ru.dgaribov.leetcode.facebook.arraysandstrings;

/**
 * @author David Garibov
 */
public class StringToInteger {
    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();
        int result = stringToInteger.myAtoi("  0000000000012345678");
        System.out.println(result);
    }
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        boolean positive = true;
        if (i == s.length()) return 0;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            if (s.charAt(i) == '-') positive = false;
            i++;
        }

        int k = i;
        while (k < s.length()) {
            if (s.charAt(k) < '0' || s.charAt(k) > '9') break;
            k++;
        }

        if (i == s.length() || i==k) return 0;
        int value;
        try {
            value = Integer.parseInt(s.substring(i, k));
        } catch(Exception ignored) {
            return positive ? Integer.MAX_VALUE: Integer.MIN_VALUE;
        }
        if (!positive) {
            value = -1 * value;
        }
        return value;
    }
}

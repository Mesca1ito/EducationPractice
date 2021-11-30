package ru.dgaribov.leetcode.microsoft.arraysandstrings;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * @author David Garibov
 */
public class ReverseWords {
    public static String reverseWords(String s) {
        String result = "";

        int from = s.length() - 1;
        int to = from;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (from != to) {
                    result += (result.isEmpty() ? "" : " ") + s.substring(from + 1, to + 1);
                }
                from--;
                to = from;
            } else {
                from--;
            }
        }
        if (from != to) result += (result.isEmpty() ? "" : " ") + s.substring(from + 1, to + 1);

        return result;
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        String result = reverseWords(s);
        System.out.println(result);
    }
}

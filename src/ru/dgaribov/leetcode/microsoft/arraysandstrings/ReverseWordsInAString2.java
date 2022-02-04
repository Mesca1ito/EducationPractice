package ru.dgaribov.leetcode.microsoft.arraysandstrings;

/**
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 * @author David Garibov
 */
public class ReverseWordsInAString2 {
    public static void main(String[] args) {
        ReverseWordsInAString2 reverseWordsInAString2 = new ReverseWordsInAString2();
        char[] words = new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWordsInAString2.reverseWords(words);
        System.out.println(words);
    }

    public void reverseWords(char[] s) {
        reverseStr(0, s.length - 1, s);
        int prevSpace = -1;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverseStr(prevSpace + 1, i - 1, s);
                prevSpace = i;
            }
        }

        reverseStr(prevSpace + 1, s.length - 1, s);
    }


    private void reverseStr(int from, int to, char[] s) {
        for (int i = from; i <= from + (to - from) / 2; i++) {
            swap(i, to - (i - from), s);
        }
    }

    private void swap(int i, int j, char[] ar) {
        char tmp = ar[j];
        ar[j] = ar[i];
        ar[i] = tmp;
    }
}

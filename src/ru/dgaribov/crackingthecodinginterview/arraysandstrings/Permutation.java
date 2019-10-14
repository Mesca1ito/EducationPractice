package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class Permutation {
    static boolean permutation(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        s = new String(sChars);
        t = new String(tChars);
        return s.equals(t);
    }
}

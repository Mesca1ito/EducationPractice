package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class IsUnique {
    public static boolean isUniqueChars(String s) {
        boolean[] chars = new boolean[128];
        for (char ch: s.toCharArray()) {
            if (chars[ch]) return false;
            chars[ch] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUniqueChars("abc"));
    }
}

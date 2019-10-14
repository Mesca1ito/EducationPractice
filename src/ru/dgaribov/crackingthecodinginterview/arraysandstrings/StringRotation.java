package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class StringRotation {
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null
        || s2 == null
        || s1.length() != s2.length()) return false;

        return isSubstring(s1 + s1, s2);
    }

    /**
     *
     * @param s1 first one
     * @param s2 second one
     * @return is s2 substring of s1
     */
    private static boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }

    public static void main(String[] args) {
        System.out.println(isRotation("abcdel", "cdeab"));
    }
}

package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class OneAway {
    public static boolean isOneAway(String first, String second) {
        if (first == null || second == null) return false;
        if (first.length() == second.length()) return checkForReplacement(first, second);
        if (first.length() - second.length() == 1) return checkForInsert(first, second);
        else if(second.length() - first.length() == 1) checkForInsert(second, first);
        return false;
    }

    private static boolean checkForReplacement(String first, String second) {
        boolean replacementOccured = false;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                if (replacementOccured) return false;
                replacementOccured = true;
            }
        }
        return true;
    }

    private static boolean checkForInsert(String longerOne, String shorterOne) {
        boolean shiftOccured = false;
        for (int i = 0, k = 0; i < shorterOne.length(); i++, k++) {
            if (shorterOne.charAt(i) != longerOne.charAt(k)) {
                if (shiftOccured) return false;
                if (shorterOne.charAt(i) != longerOne.charAt(++k)) return false;
                shiftOccured = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isOneAway("abc", "abcd"));
        System.out.println(isOneAway("abc", "adc"));
        System.out.println(isOneAway("abc", "adb"));
        System.out.println(isOneAway("aabc", "abce"));
        System.out.println(isOneAway("acc", "abc"));
        System.out.println(isOneAway("", "a"));
        System.out.println(isOneAway("b", "a"));
    }
}

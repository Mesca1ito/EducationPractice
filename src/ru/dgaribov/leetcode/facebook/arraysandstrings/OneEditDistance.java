package ru.dgaribov.leetcode.facebook.arraysandstrings;

/**
 * @author David Garibov
 */
public class OneEditDistance {
    public static void main(String[] args) {
        OneEditDistance app = new OneEditDistance();
        boolean result = app.isOneEditDistance("ab", "acb");
        System.out.println(result);
    }
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t)) return false;
        if (Math.abs(s.length() - t.length()) > 1) return false;
        String big;
        String small;
        boolean canSkip = true;
        boolean canSwap = false;
        if (s.length() > t.length()) {
            big = s;
            small = t;
        } else if (t.length() > s.length()) {
            big = t;
            small = s;
        } else {
            big = s;
            small = t;
            canSkip = false;
            canSwap = true;
        }
        if (big.length() == 1 && small.length() == 0) return true;

        int sP = 0;
        int bP = 0;

        while (bP < big.length() && sP < small.length()) {
            if (big.charAt(bP) != small.charAt(sP)) {
                if (big.length() == small.length()) {
                    if (canSwap) {
                        canSwap = false;
                        bP++;
                        sP++;
                    } else {
                        return false;
                    }
                } else {
                    if (canSkip) {
                        canSkip = false;
                        bP++;
                    } else {
                        return false;
                    }
                }
            } else {
                sP++;
                bP++;
            }
        }

        return true;
    }
}

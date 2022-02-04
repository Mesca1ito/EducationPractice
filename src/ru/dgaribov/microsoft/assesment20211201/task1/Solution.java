package ru.dgaribov.microsoft.assesment20211201.task1;

/**
 * @author David Garibov
 */
public class Solution {
    public int solution(String S, int B) {
        // write your code in Java SE 8
        return fixHoles(S, 0, B, 0, 0);
    }

    /**
     * @param S        road
     * @param start    where our recursive function start on the road
     * @param B        budget
     * @param mode     0 - if we're fixing new hole, 1 - if we're fixing consecutive
     * @param maxHoles - current number of fixed holes
     *
     * @return max number of fixed potholes
     */
    int fixHoles(String S, int start, int B, int mode, int maxHoles) {

        if (start == S.length() || B < 2) {
            return maxHoles;
        }

        if (S.charAt(start) == '.') {
            // If we were in a sequence mode - reduce budget
            if (mode == 1) {
                B -= 1;
            }
            return fixHoles(S, start + 1, B, 0, maxHoles);
        } else {
            // Three scenarios:
            // 1 - Don't fix
            int result1 = fixHoles(S, start + 1, B, mode, maxHoles);
            // 2 - Fix and continue sequence
            int result2 = fixHoles(S, start + 1, B - 1, 1, maxHoles + 1);
            // 3 - Fix and finish sequence
            int result3 = fixHoles(S, start + 1, B - 2, 0, maxHoles + 1);

            return Math.max(Math.max(result1, result2), result3);
        }

    }
}

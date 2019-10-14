package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.Arrays;

public class TripleStep {
    public int countWays(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        else return countWays(n - 3) + countWays(n - 2) + countWays(n - 1);
    }


    public int countWaysDynamic(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWays(n, memo);
    }

    private int countWays(int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (memo[n] != -1) return memo[n];
        memo[n] = countWays(n - 3, memo) + countWays(n - 2, memo) + countWays(n - 1, memo);

        return memo[n];
    }
}

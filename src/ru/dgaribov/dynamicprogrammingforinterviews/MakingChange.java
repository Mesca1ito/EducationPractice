package ru.dgaribov.dynamicprogrammingforinterviews;

public class MakingChange {
    // Brute force solution. Go through every
// combination of coins that sum up to c to // find the minimum number
    private static int[] coins = new int[]{10, 6, 1};

    private static int makeChange(int c) {
        if (c == 0) return 0;
        int minCoins = Integer.MAX_VALUE;
// Try removing each coin from the total and // see how many more coins are required
        for (int coin : coins) {
// Skip a coin if it’s value is greater // than the amount remaining
            if (c - coin >= 0) {
                int currMinCoins = makeChange(c - coin);
                if (currMinCoins < minCoins)
                    minCoins = currMinCoins;
            }
        }
        // Add back the coin removed recursively
        return minCoins + 1;
    }


    // Bottom up dynamic programming solution. // Iteratively compute number of coins for // larger and larger amounts of change
    public static int makeChangeD(int c) {
        int[] cache = new int[c + 1];
        for (int i = 1; i <= c; i++) {
            int minCoins = Integer.MAX_VALUE;
            // Try removing each coin from the total
            // and see which requires the fewest
            // extra coins
            for (int coin : coins) {
                if (i - coin >= 0) {
                    int currCoins = cache[i - coin] + 1;
                    if (currCoins < minCoins) {
                        minCoins = currCoins;
                    }
                }
            }
            cache[i] = minCoins;
        }
        return cache[c];
    }


    public static void main(String[] args) {
        int result = makeChangeD(68);
        System.out.println(result);
    }
}

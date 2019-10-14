package ru.dgaribov.hackerrank.tendaysofstatistics.day4.binominaldistribution;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution {

    /**
     * Number of successes
     */
    private static final int x = 3;

    /**
     * Number of trials
     */
    private static final int n = 6;


    /**
     * The probability of success of 1 trial
     */
    private static final BigDecimal p;

    static {
        p = new BigDecimal(1.09).divide(new BigDecimal(2.09), 3, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal result = BigDecimal.ZERO;
        for (int r = x; r <= n; r++) {
            result = result.add(findBinominal(p, r, n));
        }
        System.out.println(result);
    }

    /**
     *
     * @param p - probability of success
     * @param x - number of success
     * @param n - number of trials
     * @return probability of we have x successes of n trials
     */
    private static BigDecimal findBinominal(BigDecimal p, int x, int n) {
        return new BigDecimal(factorial(n)).divide((
                new BigDecimal(factorial(x)).multiply(
                        new BigDecimal(factorial(n - x))
                )
        ), 3, RoundingMode.HALF_UP)
                .multiply(p.pow(x))
                .multiply(
                        BigDecimal.ONE.subtract(p).pow(n - x)
                )
                .setScale(3, RoundingMode.HALF_UP);
    }

    public static int factorial(int number) {
        int result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}

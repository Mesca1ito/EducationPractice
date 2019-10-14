package ru.dgaribov.hackerrank.tendaysofstatistics.day4.binominaldistribution2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution {

    /**
     * Number of successes
     */
    private final static int x = 2;

    /**
     * Number of trials
     */
    private final static int n = 10;


    /**
     * The probability of success of 1 trial
     */
    private static BigDecimal p;

    static {
        p = new BigDecimal(0.12).setScale(3, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {


        BigDecimal result = BigDecimal.ZERO;
        for (int r = 0; r <= x; r++) {
            result = result.add(findBinominal(p, r, n));
        }
        // probability that a batch of 10 pistons will contain no more than 2 rejects.
        System.out.println(result
                .setScale(3, RoundingMode.HALF_UP));

        result = BigDecimal.ZERO;
        for (int r = x; r <= n; r++) {
            result = result.add(findBinominal(p, r, n));
        }
        // probability that a batch of 10 pistons will contain at least 2 rejects.
        System.out.println(result
                .setScale(3, RoundingMode.HALF_UP));
    }

    private static BigDecimal findBinominal(BigDecimal p, int x, int n) {
        return new BigDecimal(factorial(n)).divide((
                new BigDecimal(factorial(x)).multiply(
                        new BigDecimal(factorial(n - x))
                )
        ), 3, RoundingMode.HALF_UP)
                .multiply(p.pow(x))
                .multiply(
                        BigDecimal.ONE.subtract(p).pow(n - x)
                );
    }

    private static int factorial(int number) {
        int result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}

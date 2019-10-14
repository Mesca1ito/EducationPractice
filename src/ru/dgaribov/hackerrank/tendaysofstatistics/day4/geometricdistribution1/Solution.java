package ru.dgaribov.hackerrank.tendaysofstatistics.day4.geometricdistribution1;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The probability that a machine produces a defective product is 1/3.
 * What is the probability that the 1st defect is found during the 5th inspection?
 */
public class Solution {

    /**
     * Number of successes
     */
    private final static int x = 2;

    /**
     * Number of trials
     */
    private final static int n = 5;


    /**
     * The probability of success of 1 trial
     */
    private static final BigDecimal p = new BigDecimal(0.333).setScale(3, RoundingMode.HALF_UP);;

    public static void main(String[] args) {
        BigDecimal result = findGeometricDistribution(p, n);
        System.out.println(result);
    }

    /**
     *
     * @param p probability of success
     * @param n trials
     * @return probability of we have success after n trials
     */
    private static BigDecimal findGeometricDistribution(BigDecimal p, int n) {
        return BigDecimal.ONE.subtract(p).pow(n-1).multiply(p).setScale(3, RoundingMode.HALF_UP);
    }

    private static int factorial(int number) {
        int result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}

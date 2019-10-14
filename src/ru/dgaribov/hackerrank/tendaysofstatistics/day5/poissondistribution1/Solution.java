package ru.dgaribov.hackerrank.tendaysofstatistics.day5.poissondistribution1;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A random variable, X, follows Poisson distribution with mean of 2.5. Find the probability with which the random
 * variable X is equal to 5.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(poissonDistribution(
                new BigDecimal(2.5), 5
        ));
    }

    /**
     * @param lambda is the average number of successes that occur in a specified region
     * @param k      is the actual number of successes that occur in a specified region
     * @return the Poisson probability, which is the probability of getting exactly
     * k successes when the average number of successes is lambda
     */
    private static BigDecimal poissonDistribution(BigDecimal lambda, int k) {
        return lambda.pow(k).multiply(
                BigDecimal.valueOf(Math.pow(Math.E, lambda.doubleValue() * -1))
        )
                .divide(new BigDecimal(factorial(k)), 3, RoundingMode.HALF_UP);
    }

    private static int factorial(int number) {
        int result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}

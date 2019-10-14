package ru.dgaribov.hackerrank.tendaysofstatistics.day5.poissondistribution2;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The manager of a industrial plant is planning to buy a machine of either type A or type B. For each day’s operation:
 * <p>
 * The number of repairs, X, that machine A needs is a Poisson random variable with mean 0.88. The daily cost of operating A  is Ca = 160 + 40 X^2.
 * The number of repairs, Y, that machine B needs is a Poisson random variable with mean 1.55. The daily cost of operating B is Cb = 128 + 40 Y^2.
 * Assume that the repairs take a negligible amount of time and the machines are maintained nightly to ensure that they operate like new at the start of each day.
 * Find and print the expected daily cost for each machine.
 */
public class Solution {

    public static void main(String[] args) {
        BigDecimal Ca = new BigDecimal(160).add(
                new BigDecimal(40).multiply(
                findPoissonQuad(
                        new BigDecimal(0.88)
                )).setScale(3, RoundingMode.HALF_UP)
        );
        System.out.println(Ca);

        BigDecimal Cb = new BigDecimal(128).add(
                new BigDecimal(40).multiply(
                findPoissonQuad(
                        new BigDecimal(1.55)
                )).setScale(3, RoundingMode.HALF_UP)
        );
        System.out.println(Cb);
    }

    private static BigDecimal findPoissonQuad(BigDecimal lambda) {
        return lambda.add(lambda.pow(2));
    }
}

package ru.dgaribov.hackerrank.tendaysofstatistics.day6.centrallimittheorem3;

/**
 * You have a sample of 100 values from a population with mean 250 and with standard deviation 80. Compute the interval that
 * covers the middle  of the distribution of the sample mean; in other words, compute A and B such that P(A < x < B) = 0.95.
 * Use the value of z=1.96.
 * Note that https://en.wikipedia.org/wiki/Standard_score is the z-score.
 */
public class Solution {
    public static void main(String[] args) {
        /* Provided variables */
        double mean = 500;
        double std = 80;
        int n = 100;
        double zScore = 1.96; // equivalent to 95% confidence interval

        /* Formula (found online) for confidence interval +/- */
        double marginOfError = zScore * std / Math.sqrt(n);

        /* Print output */
        System.out.format("%.2f%n", mean - marginOfError);
        System.out.format("%.2f%n", mean + marginOfError);
    }
}
package ru.dgaribov.hackerrank.tendaysofstatistics.day5.normaldistribution1;

import java.util.Scanner;

interface MathematicalFunction {
    double apply(double x);
}

/**
 * Since we can find the probability of something defined with a probabilistic distribution function having a value
 * between a and b by integrating from a to b, we can simply manually take the integral of the given normal distribution.
 * For the first case where the lower limit is not given, it is enough to start with a value far away from the
 * mean (around 5 sigma) so that the rest of the values to the left of the value we selected are negligible
 */
class NormalDistribution implements MathematicalFunction {
    private final double mu;
    private final double sigma;

    public NormalDistribution(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }

    public double apply(double x) {
        return Math.pow(Math.E, -(Math.pow(x - mu, 2) / (2 * Math.pow(sigma, 2)))) / (sigma * Math.sqrt(2 * Math.PI));
    }
}


public class Solution {

    public static double integrate(MathematicalFunction f, double a, double b) {
        double dx = 1e-5;
        double sum = 0;
        for (double x = a; x <= b; x += dx)
            sum += f.apply(x) * dx;
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double mu = in.nextDouble();
        double sigma = in.nextDouble();
        double upperSolo = in.nextDouble();
        double lower = in.nextDouble();
        double upper = in.nextDouble();
        in.close();

        MathematicalFunction distribution = new NormalDistribution(mu, sigma);
        double p1 = integrate(distribution, mu - 5 * sigma, upperSolo);
        double p2 = integrate(distribution, lower, upper);
        System.out.printf("%.3f\n%.3f\n", p1, p2);
    }
}
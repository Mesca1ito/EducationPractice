package ru.dgaribov.hackerrank.tendaysofstatistics.day5.normaldistribution2;

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
//        double mu = in.nextDouble();
//        double sigma = in.nextDouble();
//        double highScore = in.nextDouble();
//        double passScore = in.nextDouble();
        double mu = 70;
        double sigma = 10;
        double highScore = 80;
        double passScore = 60;
        in.close();

        MathematicalFunction distribution = new NormalDistribution(mu, sigma);
        double p1 = integrate(distribution, highScore, 100) * 100;
        double p2 = integrate(distribution, passScore, 100) * 100;
        double p3 = integrate(distribution, 0, passScore) * 100;
        System.out.printf("%.2f\n%.2f\n%.2f\n", p1, p2, p3);
    }
}


//    from math import sqrt,erf
//
//        # Cumulative distribution function for the standard normal distribution
//        def cumulative(x,mu,std):
//        param = (x-mu)/(std*sqrt(2))
//        return 0.5 * ( 1 + erf(param) )
//
//        # read input
//        mu , std = [float(num) for num in input().split()]
//        a = float(input())                                # question 1
//        b = float(input())
//
//        # calculate cumulative percentage: hence 100 * x
//        ans1 = 100 * (1 - cumulative(a, mu, std))
//        ans2 = 100 * (1 - cumulative(b, mu, std))
//        ans3 = 100 * cumulative(b, mu, std)
//
//        # print results
//        print("%.2f" %ans1)
//        print("%.2f" %ans2)
//        print("%.2f" %ans3)
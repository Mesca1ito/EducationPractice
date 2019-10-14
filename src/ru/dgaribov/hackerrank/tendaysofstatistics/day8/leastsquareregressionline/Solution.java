package ru.dgaribov.hackerrank.tendaysofstatistics.day8.leastsquareregressionline;

/**
 * A group of five students enrolls in Statistics immediately after taking a Math aptitude test.
 * Each student's Math aptitude test score, x, and Statistics course grade, y, can be expressed as the following list of (x,y) points
 * <p>
 * If a student scored an 80 on the Math aptitude test, what grade would we expect them to achieve in Statistics?
 * Determine the equation of the best-fit line using the least squares method, then compute and print the value of y when x = 80 .
 */
public class Solution {
    public static void main(String[] args) {
        int[] X = {95, 85, 80, 70, 60};
        int[] Y = {85, 95, 70, 65, 70};

        System.out.format("%.2f%n", findA(X, Y) + findB(X, Y) * 80);
    }

    static double findB(int[] X, int[] Y) {
        int n = X.length;

        double sigmaXiYi = 0;
        double sigmaXi = 0;
        double sigmaYi = 0;
        double sigmaXQuadI = 0;
        for (int i = 0; i < n; i++) {
            int x = X[i];
            int y = Y[i];
            sigmaXiYi += x * y;
            sigmaXi += x;
            sigmaYi += y;
            sigmaXQuadI += Math.pow(x, 2);
        }

        return (n * sigmaXiYi - sigmaXi * sigmaYi) / (n * sigmaXQuadI - Math.pow(sigmaXi, 2));
    }

    static double findA(int[] X, int[] Y) {

        double sigmaXi = 0;
        double sigmaYi = 0;
        double sigmaXQuadI = 0;
        for (int i = 0; i < X.length; i++) {
            int x = X[i];
            int y = Y[i];
            sigmaXi += x;
            sigmaYi += y;
        }
        double xMean = sigmaXi / X.length;
        double yMean = sigmaYi / X.length;

        return yMean - findB(X, Y) * xMean;
    }
}

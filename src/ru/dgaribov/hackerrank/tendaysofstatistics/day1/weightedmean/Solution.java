package ru.dgaribov.hackerrank.tendaysofstatistics.day1.weightedmean;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution {



    private static BigDecimal findWeightedMean(int[] elements, int[] weights ) {
        int sum = 0;
        for (int i = 0; i < elements.length; i++) {
            sum += elements[i] * weights[i];
        }

        int sumOfWeights = 0;
        for (int weight: weights) {
            sumOfWeights += weight;
        }

        return new BigDecimal(sum).divide(new BigDecimal(sumOfWeights), 1, RoundingMode.HALF_UP);
    }



    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input04.txt");
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(file);
        int sizeOfArray = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int[] ar = new int[sizeOfArray];
        String[] strEl = sc.nextLine().split(" ");
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < sizeOfArray; i++) {
            ar[i] = Integer.parseInt(strEl[i]);
        }
        int[] weights = new int[sizeOfArray];
        strEl = sc.nextLine().split(" ");
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < sizeOfArray; i++) {
            weights[i] = Integer.parseInt(strEl[i]);
        }

        BigDecimal weightedMean = findWeightedMean(ar, weights);
        System.out.println(weightedMean);
    }
}

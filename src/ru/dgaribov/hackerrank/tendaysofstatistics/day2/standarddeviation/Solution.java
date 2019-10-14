package ru.dgaribov.hackerrank.tendaysofstatistics.day2.standarddeviation;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution {

    private static BigDecimal findMean(int ar[]) {
        int sum = 0;
        for (int el : ar) {
            sum += el;
        }

        return new BigDecimal(sum).divide(new BigDecimal(ar.length), 1, RoundingMode.HALF_UP);
    }

    private static void findStandardDeviation(int[] ar) {
        BigDecimal mean = findMean(ar);

        BigDecimal sumSquareDistances = BigDecimal.ZERO;
        for (int el : ar) {
            sumSquareDistances = sumSquareDistances.add(new BigDecimal(el).subtract(mean).pow(2));
        }
//        BigDecimal standardDeviation = sumSquareDistances.divide(new BigDecimal(ar.length), 1, RoundingMode.HALF_UP)
//                .sqrt(MathContext.DECIMAL32).setScale(1, RoundingMode.HALF_UP);
//        System.out.println(standardDeviation);
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test/day2/standarddeviation/input00.txt");
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

        findStandardDeviation(ar);
    }
}

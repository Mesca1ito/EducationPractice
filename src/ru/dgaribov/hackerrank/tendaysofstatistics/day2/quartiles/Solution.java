package ru.dgaribov.hackerrank.tendaysofstatistics.day2.quartiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution {

    private static void findQuartiles(int[] ar) {

        BigDecimal Q2 = findMedian(ar);
        int[] left = new int[ar.length / 2];
        for (int i = 0; new BigDecimal(ar[i]).compareTo(Q2) < 0; i++) {
            left[i] = ar[i];
        }

        int[] right = new int[ar.length / 2];
        int rightIn = 0;
        for (int i = 0; i < ar.length; i++) {
            if (new BigDecimal(ar[i]).compareTo(Q2) > 0) {
                right[rightIn++] = ar[i];
            }
        }

        BigDecimal Q1 = findMedian(left);
        BigDecimal Q3 = findMedian(right);

        System.out.println(Q1);
        System.out.println(Q2);
        System.out.println(Q3);
    }


    private static BigDecimal findMedian(int[] ar) {
        Arrays.sort(ar);
        if (ar.length % 2 == 0) {
            return new BigDecimal(ar[ar.length / 2])
                    .add(new BigDecimal(ar[ar.length / 2 - 1]))
                    .divide(new BigDecimal(2), 0, RoundingMode.HALF_UP);
        } else {
            return new BigDecimal(ar[ar.length / 2]);
        }
    }





    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input00.txt");
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

        findQuartiles(ar);
    }
}

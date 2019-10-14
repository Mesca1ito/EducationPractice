package ru.dgaribov.hackerrank.tendaysofstatistics.day2.interquartilerange;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private static void findQuartileRange(int[] ar) {

        BigDecimal Q2 = findMedian(ar);
        int[] left = getLeft(ar);
        int[] right = getRight(ar);

        BigDecimal Q1 = findMedian(left);
        BigDecimal Q3 = findMedian(right);

        System.out.println(Q3.subtract(Q1).setScale(1, RoundingMode.HALF_UP));
    }

    private static int[] getLeft(int[] ar) {
        int[] left = new int[ar.length / 2];
        System.arraycopy(ar, 0, left, 0, left.length);
        return left;
    }

    private static int[] getRight(int[] ar) {
        int middle = ar.length / 2;
        int[] right = new int[middle];
        if (ar.length % 2 == 0) System.arraycopy(ar, middle, right, 0, right.length);
        else System.arraycopy(ar, middle + 1, right, 0, right.length);
        return right;
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





    private static int elementsBeforeMedian(BigDecimal median, int ar[]) {
        int result = 0;
        for (int el: ar) {
            if (new BigDecimal(el).compareTo(median) < 0) {
                result++;
            } else {
                break;
            }
        }

        return result;
    }

    private static int elementsAfterMedian(BigDecimal median, int ar[]) {
        int result = 0;
        for (int el: ar) {
            if (new BigDecimal(el).compareTo(median) > 0) {
                result++;
            }
        }

        return result;
    }





    public static void main(String[] args) throws FileNotFoundException {
        try {
            File file = new File("test/day2/interquartilerange/input04.txt");
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

            strEl = sc.nextLine().split(" ");
            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] freq = new int[sizeOfArray];
            for (int i = 0; i < sizeOfArray; i++) {
                freq[i] = Integer.parseInt(strEl[i]);
            }

            int freqSum = 0;
            for (int freqEl : freq) {
                freqSum += freqEl;
            }

            int[] resultArr = new int[freqSum];
            int insertIndex = 0;
            for (int i = 0; i < sizeOfArray; i++) {
                for (int k = 0; k < freq[i]; k++) {
                    resultArr[insertIndex++] = ar[i];
                }
            }

            findQuartileRange(resultArr);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

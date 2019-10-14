package ru.dgaribov.hackerrank.tendaysofstatistics.day1.meanmedianmode;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution {

    private static void meanMedianAndMode(int[] ar) {

        BigDecimal mean = findMean(ar);
        System.out.println(mean.toString());

        BigDecimal median = findMedian(ar);
        System.out.println(median.toString());

        System.out.println(findMode(ar));

    }

    private static BigDecimal findMean(int ar[]) {
        int sum = 0;
        for (int el : ar) {
            sum += el;
        }

        return new BigDecimal(sum).divide(new BigDecimal(ar.length), 1, RoundingMode.HALF_UP);
    }

    private static BigDecimal findMedian(int[] ar) {
        Arrays.sort(ar);
        if (ar.length % 2 == 0) {
            return new BigDecimal(ar[ar.length / 2])
                    .add(new BigDecimal(ar[ar.length / 2 - 1]))
                    .divide(new BigDecimal(2), 1, RoundingMode.HALF_UP);
        } else {
            return new BigDecimal(ar[ar.length / 2]);
        }
    }

    private static int findMode(int[] ar) {

        Map<Integer, Integer> freqMap = new HashMap<>(ar.length);
        for (int number : ar) {
            if (freqMap.containsKey(number)) {
                Integer count = freqMap.get(number);
                freqMap.put(number, ++count);
            } else {
                freqMap.put(number, 1);
            }
        }




        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(new FrequencyComparator(freqMap));
        sortedMap.putAll(freqMap);
        return sortedMap.firstKey();

    }

    final static class FrequencyComparator implements Comparator<Integer> {
        private Map<Integer, Integer> refMap;

        FrequencyComparator(Map<Integer, Integer> refMap) {
            this.refMap = refMap;
        }


        @Override
        public int compare(Integer o1, Integer o2) {
            if (refMap.get(o1).compareTo(refMap.get(o2)) == 0) {
                return o1.compareTo(o2);
            }

            return -1 * refMap.get(o1).compareTo(refMap.get(o2));
        }
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

        meanMedianAndMode(ar);
    }
}

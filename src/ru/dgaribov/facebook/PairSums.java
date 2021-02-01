package ru.dgaribov.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class PairSums {
    int numberOfWays(int[] arr, int k) {
        int result = 0;
        Map<Integer, Integer> theMap = new HashMap<>();

        for (int i: arr) {
            theMap.merge(i, 1, Integer::sum);
        }

        for (int first: theMap.keySet()) {
            if (theMap.containsKey(k - first)) {
                int w1 = theMap.get(first);
                int w2 = theMap.get(k - first);
                if (first == (k / 2)) {
                    result += w1 * (w1 - 1);
                } else {
                    result += w1 * w2;
                }

            }
        }

        return result /= 2;
    }

    public static void main(String[] args) {
        PairSums sums = new PairSums();
        int result = sums.numberOfWays(new int[]{1, 5, 3, 3, 3}, 6);
        System.out.println(result);
    }


}

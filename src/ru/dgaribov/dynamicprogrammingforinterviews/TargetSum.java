package ru.dgaribov.dynamicprogrammingforinterviews;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    private static int targetSum(int[] nums, int T) {
        int sum = 0;
// Our cache has to range from -sum(nums) to // sum(nums), so we offset everything by sum
        for (int num : nums) sum += num;
        int[][] cache =
                new int[nums.length + 1][2 * sum + 1];
        if (sum == 0) return 0;
// Initialize i=0, T=0
        cache[0][sum] = 1;
// Iterate over previous row and update the // current row
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                int prev = cache[i - 1][j];
                if (prev != 0) {
                    cache[i][j - nums[i - 1]] += prev;
                    cache[i][j + nums[i - 1]] += prev;
                }
            }
        }
        return cache[nums.length][sum + T];
    }

    private static int targetSumRec(int[] nums, int T, int i, int sum, Map<Integer, Map<Integer, Integer>> cache) {

        if (i == nums.length) {
            return sum == T ? 1 : 0;
        }

        if (!cache.containsKey(i)) {
            cache.put(i, new HashMap<>());
        }

        Integer cached = cache.get(i).get(sum);
        if (cached != null) return cached;

        int toReturn = targetSumRec(nums, T, i + 1, sum + nums[i], cache)
                + targetSumRec(nums, T, i + 1, sum - nums[i], cache);

        cache.get(i).put(sum, toReturn);
        return toReturn;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3};
        System.out.println(targetSum(nums, 3));
    }

}

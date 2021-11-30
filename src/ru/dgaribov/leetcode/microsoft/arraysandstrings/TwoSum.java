package ru.dgaribov.leetcode.microsoft.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * @author David Garibov
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsIndexes = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (numsIndexes.containsKey(diff)) return new int[]{numsIndexes.get(diff), i};
            numsIndexes.put(nums[i], i);
        }
        return new int[]{};
    }
}

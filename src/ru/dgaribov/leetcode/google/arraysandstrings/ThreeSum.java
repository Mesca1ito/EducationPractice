package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.*;

/**
 * @author David Garibov
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] ar = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(ar));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Integer> numsMap = arrayToMap(nums);
        for (int num : nums) {
            Set<List<Integer>> twoSums = twoSum(-1 * num, numsMap);
            for (List<Integer> twoSum : twoSums) {
                if (twoSum.size() == 0) continue;
                twoSum.add(num);
                Collections.sort(twoSum);
                result.add(twoSum);
            }
        }

        return new ArrayList<>(result);
    }

    static Set<List<Integer>> twoSum(int value, Map<Integer, Integer> nums) {
        Set<List<Integer>> twoSumList = new HashSet<>();
        for (int one : nums.keySet()) {
            if (one == value * -1) continue;
            int two = value - one;
            if (two == value * -1) continue;

            if (nums.containsKey(two)) {
                List<Integer> listOfTwo = new ArrayList<>(2);
                listOfTwo.add(one);
                listOfTwo.add(two);
                twoSumList.add(listOfTwo);
            }
        }

        return twoSumList;
    }

    static Map<Integer, Integer> arrayToMap(int[] ar) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int el : ar) {
            map.merge(el, 1, Integer::sum);
        }
        return map;
    }



    public List<List<Integer>> threeSum2(int[] array) {
        Arrays.sort(array);
        Set<List<Integer>> triplets = new HashSet<>();
        for (int left = 0; left < array.length - 2; left++) {
            int mid = left + 1;
            int right = array.length - 1;
            while (mid < right) {
                int sum = array[left] + array[mid] + array[right];
                if (sum < 0) {
                    mid++;
                } else if (sum > 0) {
                    right--;
                } else {
                    List<Integer> triplet = Arrays.asList(array[left], array[mid], array[right]);
                    triplets.add(triplet);
                    mid++;
                }
            }
        }
        return new ArrayList<>(triplets);
    }


    public List<List<Integer>> threeSum3(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }

}

package ru.dgaribov.leetcode.facebook.arraysandstrings;

import java.util.*;

/**
 * @author David Garibov
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        System.out.println(result);
    }


    Map<Integer, Integer> numMap;

    private Map<Integer, Integer> buildNumSet(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.merge(num, 1, Integer::sum);
        }
        return numMap;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        numMap = buildNumSet(nums);

        Set<List<Integer>> result = new HashSet<>();
        for (int num : nums) {
            List<List<Integer>> twoSums = twoSum(-1 * num, numMap);
            for (List<Integer> twoSum : twoSums) {
                twoSum.add(num);
                Collections.sort(twoSum);
                result.add(twoSum);
            }
        }
        return new ArrayList<>(result);

    }

    List<List<Integer>> twoSum(int value, Map<Integer, Integer> numMap) {
        List<List<Integer>> result = new ArrayList<>();
        for (int first : numMap.keySet()) {
            int second = value - first;
            if (numMap.containsKey(second)) {
                int quantity = numMap.get(second);
                if (first == second) quantity--;
                if (value * -1 == first || value == first) quantity--;
                if (value * -1 == second || value == second) quantity--;
                if (quantity < 1) continue;
                List<Integer> twoList = new ArrayList<>(2);
                twoList.add(first);
                twoList.add(second);
                result.add(twoList);
            }
        }
        return result;
    }
}

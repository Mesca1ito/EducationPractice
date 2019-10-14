package ru.dgaribov.leetcode.medianoftwosortedarrays;

public class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] comb = combineTwoSortedArrays(nums1, nums2);
        if (comb.length % 2 == 0) {
            return 1.0 * (comb[comb.length / 2 - 1] + comb[comb.length / 2]) / 2;
        } else {
            return comb[comb.length / 2];
        }
    }

    private static int[] combineTwoSortedArrays(int[] nums1, int[] nums2) {

        int[] resultArray = new int[nums1.length + nums2.length];
        int elNum = 0;
        for (int i=0, j=0; i < nums1.length || j < nums2.length;) {
            if (i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) {
                resultArray[elNum++] = nums1[i++];
            } else if (i >= nums1.length || nums2[j] <= nums1[i]) {
                resultArray[elNum++] = nums2[j++];
            } else {
                break;
            }
        }

        return resultArray;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 4, 8};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}

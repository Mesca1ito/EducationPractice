package ru.dgaribov.leetcode.microsoft.sorting;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 * @author David Garibov
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length - 1;
        for (int i = 0; i <= p2;) {
            if (nums[i] == 0) {
                swap(nums, p0++, i);
                i++;
            } else if (nums[i] == 2) {
                swap(nums, p2--, i);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] ar, int index1, int index2) {
        int temp = ar[index2];
        ar[index2] = ar[index1];
        ar[index1] = temp;
    }
}

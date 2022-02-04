package ru.dgaribov.leetcode.microsoft.sorting;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * @author David Garibov
 */
public class SearchA2DMatrix {
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
        int[][] matrix = new int[][]{{1}};
        boolean res = searchMatrix(matrix, 1);
        System.out.println(res);
    }



    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = binarySearchRow(0, matrix.length - 1, matrix, target);
        if (row == -1) return false;



        return binSearch(0, matrix[row].length - 1, matrix[row], target);
    }

    static boolean binSearch(int left, int right, int[] ar, int target) {
        int mid = (left + right) / 2;
        if (target == ar[mid]) return true;
        if (target > ar[mid]) {
            if (mid == ar.length - 1) return false;
            if (target < ar[mid + 1]) return false;
            return binSearch(mid + 1, right, ar, target);
        } else if (target < ar[mid]) {
            if (mid == 0) return false;
            if (target > ar[mid - 1]) return false;
            return binSearch(left, mid - 1, ar, target);
        }
        return false;
    }

    static int binarySearchRow(int left, int right, int[][] matrix, int target) {
        if (matrix.length == 0) return -1;
        if (right < left) {
            return -1;
        }
        int middle = (right + left) / 2;
        if (middle < 0 || middle >= matrix.length) return -1;
        if (middle == 0 && target < matrix[middle][0]) {
            return -1;
        } else if (middle == matrix.length - 1) {
            return middle;
        } else if (matrix[middle][0] > target && matrix[middle - 1][0] <= target) {
            return middle - 1;
        } else if (matrix[middle][0] <= target && matrix[middle + 1][0] > target) {
            return middle;
        } else if (middle > 0 && matrix[middle - 1][0] > target) {
            return binarySearchRow(left, middle - 1, matrix, target);
        } else {
            return binarySearchRow(middle + 1, right, matrix, target);
        }
    }
}

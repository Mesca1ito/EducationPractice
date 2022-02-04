package ru.dgaribov.leetcode.microsoft.arraysandstrings;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate
 * another 2D matrix and do the rotation.
 *
 * @author David Garibov
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }

        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // save top
                int top = matrix[first][i];
                // left -> top
                matrix[first][i] = matrix[last - offset][first];
                // bottom -> left
                matrix[last-offset][first] = matrix[last][last - offset];
                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                // top -> right
                matrix[i][last] = top;

            }

        }

    }
}

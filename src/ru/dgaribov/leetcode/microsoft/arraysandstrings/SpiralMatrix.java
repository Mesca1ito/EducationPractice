package ru.dgaribov.leetcode.microsoft.arraysandstrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * @author David Garibov
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int topRow = 0;
        int lowestRow = matrix.length - 1;

        int leftColumn = 0;
        int rightColumn = matrix[0].length - 1;

        final int RIGHT = 0;
        final int DOWN = 1;
        final int LEFT = 2;
        final int UP = 3;

        int direction = RIGHT;

        while (topRow <= lowestRow && leftColumn <= rightColumn) {
            // move right
            if (direction == RIGHT) {

                for (int i = leftColumn; i <= rightColumn; i++) {
                    result.add(matrix[topRow][i]);
                }
                topRow++;
                direction = DOWN;
                continue;
            }
            // move down
            if (direction == DOWN) {
                for (int i = topRow; i <= lowestRow; i++) {
                    result.add(matrix[i][rightColumn]);
                }
                rightColumn--;
                direction = LEFT;
                continue;
            }

            // move left
            if (direction == LEFT) {
                for (int i = rightColumn; i >= leftColumn; i--) {
                    result.add(matrix[lowestRow][i]);
                }
                lowestRow--;

                direction = UP;
                continue;
            }

            // move up
            if (direction == UP) {
                for (int i = lowestRow; i >= topRow; i--) {
                    result.add(matrix[i][leftColumn]);
                }
                leftColumn++;

                direction = RIGHT;
                continue;
            }

            break;

        }


        return result;

    }

    public static void main(String[] args) {
//        List<Integer> result = spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        List<Integer> result = spiralOrder(new int[][]
                                                   {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        System.out.println(result);
    }
}

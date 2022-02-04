package ru.dgaribov.leetcode.microsoft.arraysandstrings;

/**
 * @author David Garibov
 */
public class SetZeros {
    public void setZeroes(int[][] matrix) {
        boolean rowHasZero = false;
        boolean columnHasZero = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowHasZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                columnHasZero = true;
                break;
            }
        }


        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                nullifyCol(matrix, i);
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }

        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }

        if (columnHasZero) {
            nullifyCol(matrix, 0);
        }
    }

    private void nullifyRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }

    private void nullifyCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
}

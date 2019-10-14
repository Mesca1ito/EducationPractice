package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class EightQueens {

    private static final int GRID_SIZE = 8;

    private void placeQueens(int row, Integer[] columns, List<Integer[]> result) {
        if (row == GRID_SIZE) {
            result.add(columns.clone());
            return;
        }

        for (int col = 0; col < GRID_SIZE; col++) {
            if (checkValid(columns, row, col)) {
                columns[row] = col;
                placeQueens(row + 1, columns, result);
            }
        }
    }


    private boolean checkValid(Integer[] columns, int row1, int col1) {
        for (int row2 = 0; row2 < row1; row2++) {
            int col2 = columns[row2];
            if (col1 == col2) return false;

            int colDistance = Math.abs(col1 - col2);
            int rowDistance = row1 - row2;

            if (colDistance == rowDistance) return false; // in this case to cell share same diagonal, which is violation,
            // according to requisites of the task
        }

        return true;
    }

    public static void main(String[] args) {

        Integer[] columns = new Integer[GRID_SIZE];
        List<Integer[]> result = new ArrayList<>(GRID_SIZE);
        new EightQueens().placeQueens(0, columns, result);
        System.out.println(result);
    }

}

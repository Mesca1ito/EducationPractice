package ru.dgaribov.udemy.essentialcodinginterviewquestions.twodarrays;

public class MS {
    public static void main(String[] args) {
        // NOTE: The following input values will be used for testing your solution.
        int[][] bombs1 = {{0, 2}, {2, 0}};
        // mineSweeper(bombs1, 3, 3) should return:
        // [[0, 1, -1],
        //  [1, 2, 1],
        //  [-1, 1, 0]]

        int[][] bombs2 = {{0, 0}, {0, 1}, {1, 2}};
        // mineSweeper(bombs2, 3, 4) should return:
        // [[-1, -1, 2, 1],
        //  [2, 3, -1, 1],
        //  [0, 1, 1, 1]]

        int[][] bombs3 = {{1, 1}, {1, 2}, {2, 2}, {4, 3}};
        // mineSweeper(bombs3, 5, 5) should return:
        // [[1, 2, 2, 1, 0],
        //  [1, -1, -1, 2, 0],
        //  [1, 3, -1, 2, 0],
        //  [0, 1, 2, 2, 1],
        //  [0, 0, 1, -1, 1]]

        int[][] result = mineSweeper(bombs3, 5, 5);
        System.out.println("");
    }

    // Implement your solution below.
    public static int[][] mineSweeper(int[][] bombs, int numRows, int numCols) {
        int[][] result = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            columns:
            for (int j = 0; j < numCols; j++) {

                for (int[] bomb : bombs) {
                    if (i == bomb[0]
                            && j == bomb[1]) {
                        result[i][j] = -1;
                        continue columns;
                    }

                    if (bomb[0] == i - 1
                            && bomb[1] == j) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i + 1
                            && bomb[1] == j) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i
                            && bomb[1] == j - 1) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i
                            && bomb[1] == j + 1) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i + 1
                            && bomb[1] == j + 1) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i - 1
                            && bomb[1] == j + 1) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i - 1
                            && bomb[1] == j - 1) {
                        result[i][j] += 1;
                        continue;
                    }

                    if (bomb[0] == i + 1
                            && bomb[1] == j - 1) {
                        result[i][j] += 1;
                    }
                }

            }
        }

        return result;
    }
}

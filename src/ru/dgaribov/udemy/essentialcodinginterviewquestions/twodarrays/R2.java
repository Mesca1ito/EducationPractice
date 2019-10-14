package ru.dgaribov.udemy.essentialcodinginterviewquestions.twodarrays;

public class R2 {
    public static void main(String[] args) {
        // NOTE: The following input values will be used for testing your solution.
        int a1[][] = {{1, 2, 3},
                      {4, 5, 6},
                      {7, 8, 9}};
        // rotate(a1, 3) should return:
        // [[7, 4, 1],
        //  [8, 5, 2],
        //  [9, 6, 3]]

        int a2[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        // rotate(a2, 4) should return:
        // [[13, 9, 5, 1],
        //  [14, 10, 6, 2],
        //  [15, 11, 7, 3],
        //  [16, 12, 8, 4]]
    }

    public static int[][] rotate(int[][] a, int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[] newCoords = rotateCoords(i, j, n);
                result[newCoords[0]][newCoords[1]] = a[i][j];
            }
        }

        return result;
    }


    private static int[] rotateCoords(int i, int j, int n) {
        return new int[]{j, n - i - 1};
    }


}

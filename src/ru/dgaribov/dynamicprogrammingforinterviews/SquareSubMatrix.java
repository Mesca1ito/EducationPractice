package ru.dgaribov.dynamicprogrammingforinterviews;

public class SquareSubMatrix {
    public static int squareSubMatrix(boolean[][] arr) {

        int max = 0;

        int[][] cache = new int[arr.length][arr[0].length];


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                max = Math.max(max, squareSubMatrix(arr, i, j, cache));
            }
        }
        return max;
    }

    public static int squareSubMatrixIterative(boolean[][] arr) {
        int max = 0;
        int[][] cache = new int[arr.length][arr[0].length];

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                if (!arr[i][j]) continue;
                if (i == 0 || j == 0) {
                    cache[i][j] = 1;
                } else {
                    cache[i][j] = 1 + Math.min(Math.min(cache[i - 1][j], cache[i][j - 1]), cache[i - 1][j - 1]);
                }
                if (cache[i][j] > max) {
                    max = cache[i][j];
                    System.out.println("i = " + i + ", j = " + j + ", max = " + max);
                }
            }
        }

        return max;
    }

    private static int squareSubMatrix(boolean[][] arr, int i, int j) {
        if (i >= arr.length || j >= arr[0].length || !arr[i][j]) return 0;
        if (i == 0 || j == 0) return 1;
        return 1
                + Math.min(Math.min(squareSubMatrix(arr, i + 1, j), squareSubMatrix(arr, i, j + 1)),
                squareSubMatrix(arr, i + 1, j + 1));
    }

    private static int squareSubMatrix(boolean[][] arr, int i, int j, int[][] cache) {
        if (i >= arr.length || j >= arr[0].length || !arr[i][j]) return 0;

        
        if (cache[i][j] > 0) {
            System.out.println("i = " + i + ", j = " + j + " cache = " + cache[i][j]);
            return cache[i][j];
        }
        cache[i][j] = 1
                + Math.min(Math.min(squareSubMatrix(arr, i + 1, j, cache), squareSubMatrix(arr, i, j + 1, cache)),
                squareSubMatrix(arr, i + 1, j + 1, cache));
        System.out.println("i = " + i + ", j = " + j + " cache = " + cache[i][j]);
        return cache[i][j];
    }


    public static void main(String[] args) {
        boolean[][] arr = new boolean[][]{
                {true, true, true, true, true},
                {true, true, true, true, false},
                {true, true, true, true, false},
                {true, true, true, true, false},
                {false, true, true, true, false}};

        System.out.println(squareSubMatrix(arr));
    }
}

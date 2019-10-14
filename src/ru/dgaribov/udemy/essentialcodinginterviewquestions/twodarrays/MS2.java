package ru.dgaribov.udemy.essentialcodinginterviewquestions.twodarrays;

import java.util.ArrayDeque;

public class MS2 {
    public static void main(String[] args) {
        // NOTE: The following input values will be used for testing your solution.
        int[][] field1 = {{0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, -1, 1, 0}};

        // click(field1, 3, 5, 2, 2) should return:
        // [[0, 0, 0, 0, 0],
        //  [0, 1, 1, 1, 0],
        //  [0, 1, -1, 1, 0]]


        // click(field1, 3, 5, 1, 4) should return:
        // [[-2, -2, -2, -2, -2],
        //  [-2, 1, 1, 1, -2],
        //  [-2, 1, -1, 1, -2]]


        int[][] field2 = {{-1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, -1}};


        // click(field2, 4, 4, 0, 1) should return:
        // [[-1, 1, 0, 0],
        //  [1, 1, 0, 0],
        //  [0, 0, 1, 1],
        //  [0, 0, 1, -1]]

        // click(field2, 4, 4, 1, 3) should return:
        // [[-1, 1, -2, -2],
        //  [1, 1, -2, -2],
        //  [-2, -2, 1, 1],
        //  [-2, -2, 1, -1]]

        int[][] result = click(field2, 4, 4, 1, 3);
        System.out.println(result);

    }

    // Implement your solution below.
    public static int[][] click(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
        expand(field, numRows, numCols, givenI, givenJ);
        return field;
    }

    private static void expand(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
        if (givenI < 0
                || givenI >= numRows
                || givenJ < 0
                || givenJ >= numCols
                || field[givenI][givenJ] != 0) return;

        field[givenI][givenJ] = -2;

        for (int i = givenI - 1; i <= givenI + 1; i++) {
            for (int j = givenJ - 1; j <= givenJ + 1; j++) {
                expand(field, numRows, numCols, i, j);
            }
        }
    }

    private static void expandInDepth(int[][] field, int numRows, int numCols, int givenI, int givenJ) {

        ArrayDeque<Coords> coordsStack = new ArrayDeque<>();

        Coords theCell = new Coords(givenI, givenJ);

        if (givenI >= 0 && givenI < numRows && givenJ >= 0 && givenJ < numCols && field[givenI][givenJ] == 0) {
            coordsStack.push(theCell);
            field[givenI][givenJ] = -2;
        }

        while (!coordsStack.isEmpty()) {
            Coords unvisitedCell = getUnvisitedCell(field, numRows, numCols, coordsStack.peek());
            if (unvisitedCell == null) {
                coordsStack.pop();
                continue;
            }
            field[unvisitedCell.x][unvisitedCell.y] = -2;
            coordsStack.push(unvisitedCell);
        }

    }

    private static void expandInBreadth(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
        ArrayDeque<Coords> coordsQueue = new ArrayDeque<>();
        Coords theCell = new Coords(givenI, givenJ);

        if (givenI >= 0 && givenI < numRows && givenJ >= 0 && givenJ < numCols && field[givenI][givenJ] == 0) {
            coordsQueue.add(theCell);
            field[givenI][givenJ] = -2;
        }

        while (!coordsQueue.isEmpty()) {
            Coords c1 = coordsQueue.remove();
            Coords c2;
            while ((c2 = getUnvisitedCell(field, numRows, numCols, c1)) != null) {
                field[c2.x][c2.y] = -2;
                coordsQueue.add(c2);
            }
        }
    }

    public static Coords getUnvisitedCell(int[][] field, int numRows, int numCols, Coords coords) {
        for (int i = coords.x - 1; i <= coords.x + 1; i++) {
            for (int j = coords.y - 1; j <= coords.y + 1; j++) {
                if (i >= 0 && i < numRows && j >= 0 && j < numCols && field[i][j] == 0) {
                    return new Coords(i, j);
                }
            }
        }

        return null;
    }


    static class Coords {

        int x;
        int y;


        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

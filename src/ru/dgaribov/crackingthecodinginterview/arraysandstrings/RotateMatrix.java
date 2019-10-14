package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class RotateMatrix {
    public static boolean rotate(int[][] matrix) {
        if (matrix == null
        || matrix.length == 0
        || matrix.length != matrix[0].length) return false;

        int n = matrix.length;
        for (int layer = 0; layer < n/2; layer++) { //way inwards
            int last = n - 1 - layer;
            for (int i = layer; i < last; i++) {
                int offset = i - layer;
                int top = matrix[layer][i];

                //left -> top
                matrix[layer][i] = matrix[last - offset][layer];

                //bottom -> left
                matrix[last - offset][layer] = matrix[last][last - offset];

                //right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                //top -> right
                matrix[i][last] = top; //right <- saved top
            }


        }

        return true;
    }
}

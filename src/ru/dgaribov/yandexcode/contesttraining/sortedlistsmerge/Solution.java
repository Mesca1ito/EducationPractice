package ru.dgaribov.yandexcode.contesttraining.sortedlistsmerge;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraysNum = Integer.parseInt(scanner.nextLine());

        int[][] input = new int[arraysNum][];

        for (int i = 0; i < arraysNum; i++) {
            int subArrayLength = scanner.nextInt();
            input[i] = new int[subArrayLength];
            for (int k = 0; k < subArrayLength; k++) {
                input[i][k] = scanner.nextInt();
            }
        }

        int[] result = mergeArrays(input);
        for (int el : result) {
            System.out.print(el + " ");
        }
    }


    private static int[] mergeArrays(int[][] arrays) {

        int sumLength = 0;
        for (int[] ar : arrays) {
            sumLength += ar.length;
        }

        if (sumLength < 1) return new int[0];

        int[] pointers = new int[arrays.length];
        int resultPointer = 0;
        int[] result = new int[sumLength];

        do {
            int smallest = 0;
            for (int i = 0; i < arrays.length; i++) {

                if (pointers[i] >= arrays[i].length) continue;

                if (arrays[i][pointers[i]]
                        <
                        arrays[smallest][pointers[smallest]]) {
                    smallest = i;
                }
            }

            if (arrays[smallest].length > pointers[smallest])
                result[resultPointer++] = arrays[smallest][pointers[smallest]++];
        } while (resultPointer < sumLength);

        return result;
    }
}

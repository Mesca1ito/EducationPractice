package ru.dgaribov.udemy.essentialcodinginterviewquestions.arrays;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsInTwoSortedArrays {
    static Integer [] commonElements(int[]array1, int[]array2) {
        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) return null;
        List<Integer> result = new ArrayList<>();

        for (int i = 0, j = 0; i < array1.length && j < array2.length; ) {
            if (array1[i] == array2[j]) {
                result.add(array1[i]);
                i++;
                j++;
                continue;
            }

            if (array1[i] < array2[j]) {
                i++;
                continue;
            }

            if (array1[i] > array2[j]) {
                j++;
            }
        }

        Integer[] resultArr = new Integer[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }

    public static void main(String[] args) {
        Integer[] result = commonElements(
                new int[] {1, 3 ,4, 6, 7, 9},
                new int[] {1, 2, 4, 5, 9, 10 }
        );
        for (int el: result) {
            System.out.print(el + " ");
        }
    }
}

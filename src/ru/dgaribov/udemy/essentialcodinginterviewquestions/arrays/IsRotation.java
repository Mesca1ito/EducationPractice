package ru.dgaribov.udemy.essentialcodinginterviewquestions.arrays;

public class IsRotation {
    static boolean isRotation(int[] array1, int[] array2) {
        if (array1 == null || array2 == null || array1.length == 0 || array2.length == 0) return false;
        int base = array2[0];
        int aPointer = 0;
        for (;aPointer < array1.length; aPointer++) {
            if (array1[aPointer] == base) break;
        }

        if (aPointer == array1.length) return false;

        for (int anArray2 : array2) {
            if (aPointer == array1.length) aPointer = 0;
            if (anArray2 != array1[aPointer++]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isRotation(new int[] {7, 1, 2, 3, 4, 5, 6},
                new int[] {4, 5, 6, 7, 1, 2, 3}));
    }
}

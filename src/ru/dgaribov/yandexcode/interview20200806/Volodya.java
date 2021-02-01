package ru.dgaribov.yandexcode.interview20200806;

/**
 * @author David Garibov
 */
public class Volodya {

//    Дана квадратная матрица NxN, заполненная нулями и единицами таким образом, что
//    в каждой строке все нули располагаются левее всех единиц
//            (возможны строки, состоящие полностью из нулей или полностью из единиц).
//    Требуется определить номер первой колонки, в которой есть хоть одна единица.
//
//    i    0 1 2 3
//            [      +
//            [0,0,0,1],
//            [0,0,1,1],
//            [0,1,1,1],
//            [1,1,1,1],
//            ]
    int findFirst1(int[][] ar) {
        int curIndex = ar[0].length;
        outer:
        for (int i = 0; i < ar.length; i++) {
            for (int j = curIndex - 1; j >= 0; j--) {
                if (ar[i][j] == 1) {
                    curIndex = j;
                    continue outer;
                }
            }
        }

        if (curIndex == ar[0].length) return Integer.MIN_VALUE;
        return curIndex;
    }



//        ===============
//    Необходимо реализовать
//    функцию,
//    которая переставляет
//    все нули
//    в массиве
//    в конец,
//    сохраняя последовательность
//    остальных значений.
//
//
//            [1,2,3,0,4,5,0,0,7,0]
//
//            [1,2,3,4,5,7,0,0,0,0]
//
//
//            [1,2,3,4,5,6,0,0,0,0]


    int[] moveZeros(int[] ar) {
        LastZeroIndex index = new LastZeroIndex();
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == 0) {
                int distance = findDistance(ar, i, index);
                swap(ar, i, i + distance);
            }
        }
        return ar;
    }


    int findDistance(int[] ar, int i, LastZeroIndex index) {
        int distance = 0;
        for (int k = i; k < ar.length; k++) {
            distance++;
            if (ar[k] != 0) return distance;
        }
        return distance;
    }

    void swap(int[] ar, int pos1, int pos2) {

    }


    class LastZeroIndex {
        int pos;
    }

}
//[10,0,0,0,0,0,0,0,0]




package ru.dgaribov.yandexcode.interview20191025;

public class BiggestSequenceWithZero {
    private static int findBiggestSequenceWithOneZero(int[] ar) {
        int lastSeenZero;
        boolean thereIsOneZero = false;
        int biggestSeq = 0;
        int curSeq = 0;

        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == 0) {
                lastSeenZero = i;
                if (thereIsOneZero) {
                    if (prevWasNotZero(i, ar)) biggestSeq = Math.max(curSeq + 1, biggestSeq);
                    curSeq = 0;
                    thereIsOneZero = false;
                    i = lastSeenZero;
                    continue;
                }
                if (prevWasNotZero(i, ar)) thereIsOneZero = true;
                continue;
            }
            biggestSeq = Math.max(++curSeq, biggestSeq);
        }
        if (prevWasNotZero(ar.length, ar) && thereIsOneZero) biggestSeq += 1;
        return biggestSeq;
    }

    private static boolean prevWasNotZero(int i, int[] ar) {
        return i > 0 && ar[i - 1] != 0;
    }


    public static void main(String[] args) {
//        int[] ar = {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1};
//        int[] ar = {0, 0, 1, 0};
        int[] ar = {1, 0, 1, 0};
        System.out.println(findBiggestSequenceWithOneZero(ar));
    }
}

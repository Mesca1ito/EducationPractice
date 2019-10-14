package ru.dgaribov.hackerrank.interviewpreparationkit.dynamicprogramming;

public class Candies {
    static long candies(int n, int[] arr) {
        long result = 0;
        long[] cache = new long[n];
        for (int i = 0; i < n; i++) {
            result += candiesRec(i, arr, cache);
        }

        return result;
    }


    static long candiesRec(int i, int[] arr, long[] cache) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) return 1;

        if (cache[i] != 0) return cache[i];

        if (i == 0 && arr[i] <= arr[i + 1]) {
            cache[i] = 1;
            return cache[i];
        }

        if (i == arr.length - 1 && arr[i] <= arr[i - 1]) {
            cache[i] = 1;
            return cache[i];
        }

        if (i > 0 && i < arr.length - 1
                && arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
            cache[i] = 1;
            return cache[i];
        }

        if (i > 0 && i < arr.length - 1
                && arr[i] == arr[i - 1] && arr[i] < arr[i + 1]) {
            cache[i] = 1;
            return cache[i];
        }

        if (i > 0 && i < arr.length - 1
                && arr[i] < arr[i - 1] && arr[i] == arr[i + 1]) {
            cache[i] = 1;
            return cache[i];
        }


        long leftResult = 0;
        long rightResult = 0;
        if (i > 0 && arr[i] >= arr[i - 1]) {
            if (arr[i] == arr[i - 1]) leftResult = cache[i - 1];
            else {
                leftResult = candiesRec(i - 1, arr, cache) + 1;
            }

        }
        if (i < arr.length - 1 && arr[i] >= arr[i + 1]) {
            rightResult = candiesRec(i + 1, arr, cache);
            if (arr[i] > arr[i + 1]) rightResult += 1;
        }
        cache[i] = leftResult >= rightResult ? leftResult : rightResult;
        return cache[i];
    }

    public static void main(String[] args) {
        System.out.println(candies(5, new int[]{5, 4, 3, 4, 5}));
        System.out.println(candies(5, new int[]{3, 4, 5, 4, 3}));
        System.out.println(candies(5, new int[]{3, 4, 5, 4, 4}));
        System.out.println(candies(5, new int[]{3, 3, 3, 3, 3}));
        System.out.println(candies(5, new int[]{3, 3, 3, 3, 99}));
        System.out.println(candies(5, new int[]{99, 3, 3, 3, 3}));
        System.out.println(candies(4, new int[]{5, 4, 4, 6}));
        System.out.println(candies(4, new int[]{5, 4, 4, 1}));
        System.out.println(candies(4, new int[]{99, 3, 3, 99}));
        System.out.println(candies(5, new int[]{99, 3, 3, 3, 99}));
        System.out.println(candies(7, new int[]{2, 1, 1, 2, 1, 2, 1}));
    }
}

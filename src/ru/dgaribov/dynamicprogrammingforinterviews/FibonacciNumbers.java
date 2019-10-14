package ru.dgaribov.dynamicprogrammingforinterviews;

public class FibonacciNumbers {

    public static int find(int n) {
        int[] ar = new int[n];
        ar[0] = 0;
        ar[1] = 1;
        if (n < 3) return ar[n - 1];
        for (int i = 2; i < n; i++) {
            ar[i] = ar[i - 2] + ar[i - 1];
        }

        return ar[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(find(999));
    }
}

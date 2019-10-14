package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

public class RecursiveMultiply {
    public static int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelper(smaller, bigger);
    }

    private static int minProductHelper(int smaller, int bigger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        int s = smaller >> 1; // Divide by 2
        int half = minProductHelper(s, bigger);
        if (smaller % 2 == 1) {
            return half + half + bigger;
        }
        return half + half;
    }

    public static void main(String[] args) {
        System.out.println(minProduct(3, 3));
    }
}

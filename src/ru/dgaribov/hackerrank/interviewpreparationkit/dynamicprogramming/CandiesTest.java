package ru.dgaribov.hackerrank.interviewpreparationkit.dynamicprogramming;


import org.testng.Assert;
import org.testng.internal.collections.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static ru.dgaribov.hackerrank.interviewpreparationkit.dynamicprogramming.Candies.candies;

public class CandiesTest {
    @org.junit.Test
    public void test() throws FileNotFoundException {
        Assert.assertEquals(11, candies(5, new int[]{5, 4, 3, 4, 5}));
        Assert.assertEquals(9, candies(5, new int[]{3, 4, 5, 4, 3}));
        Assert.assertEquals(8, candies(5, new int[]{3, 4, 5, 4, 4}));
        Assert.assertEquals(5, candies(5, new int[]{3, 3, 3, 3, 3}));
        Assert.assertEquals(6, candies(5, new int[]{3, 3, 3, 3, 99}));
        Assert.assertEquals(6, candies(5, new int[]{99, 3, 3, 3, 3}));
        Assert.assertEquals(6, candies(4, new int[]{5, 4, 4, 6}));
        Assert.assertEquals(6, candies(4, new int[]{5, 4, 4, 1}));
        Assert.assertEquals(6, candies(4, new int[]{99, 3, 3, 99}));
        Assert.assertEquals(7, candies(5, new int[]{99, 3, 3, 3, 99}));
        Assert.assertEquals(10, candies(7, new int[]{2, 1, 1, 2, 1, 2, 1}));

        Pair<Integer, int[]> fileContents = readFile("input03.txt");
        Assert.assertEquals(160929, candies(fileContents.first(), fileContents.second()));

    }

    private static Pair<Integer, int[]> readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);


        scanner.close();
        return new Pair<>(n, arr);
    }



}

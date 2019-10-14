package ru.dgaribov.hackerrank.algorithms.strings.camelcase;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

    // Complete the camelcase function below.
    static int camelcase(String s) {
        char[] chars = s.toCharArray();
        if (chars == null || chars.length == 0) return 0;
        int result = 1;
        for (char theChar: chars) {
            if (Character.isUpperCase(theChar)) result +=1;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        int result = camelcase(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
        new TreeMap<>().lastEntry();

        scanner.close();

    }
}
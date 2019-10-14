package ru.dgaribov.hackerrank.interviewpreparationkit.dynamicprogramming;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Abbreviation {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        int upperCaseChars = 0;
        List<Integer> startPositions = new ArrayList<>();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        for (int i = 0; i < aChars.length; i++) {
            char ch = aChars[i];
            if (Character.isUpperCase(ch)) {
                upperCaseChars++;
            }

            if (Character.toUpperCase(ch) == b.charAt(0)) {
                startPositions.add(i);
            }
        }
        if (upperCaseChars > b.length()) return "NO";


        List<Integer> results = new ArrayList<>(startPositions.size());
        for (int startPosition: startPositions) {
            int result = charsToUpper(aChars, startPosition, bChars, 0);
            if (result != -1) results.add(result);
        }
        if (results.isEmpty()) return "NO";
        Collections.sort(results);
        if (upperCaseChars + results.get(0) > b.length()) return "NO";
        return "YES";

    }

    private static int charsToUpper(char[] a, int aPos, char[] b, int bPos) {
        int charsToUpper = 0;
        for (int i = aPos; i < a.length; i++) {
            if (bPos >= b.length) break;
            if (Character.toUpperCase(a[i]) == b[bPos]) {
                if (Character.isLowerCase(a[i])) charsToUpper++;
                bPos++;
            }
        }
        if (bPos != b.length) return -1;
        return charsToUpper;
    }
    private static Scanner scanner = null;

    static {
        try {
            scanner = new Scanner(new File("dp-input06.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            System.out.println(result);
        }

        scanner.close();
    }
}

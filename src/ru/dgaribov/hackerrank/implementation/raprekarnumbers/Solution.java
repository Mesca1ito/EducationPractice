package ru.dgaribov.hackerrank.implementation.raprekarnumbers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the kaprekarNumbers function below.
    static void kaprekarNumbers(int p, int q) {
        List<String> result = new LinkedList<>();
        for (; p <= q && q <= 100000; p++) {

            if (p < 9) {
                if (p == 1) result.add(String.valueOf(p));
                continue;
            }

            int length = String.valueOf(p).length();
            String square = String.valueOf((long) p * p);
            String secondPart;
            String firstPart;
            try {
                secondPart = square.substring(square.length() - length);
                firstPart = square.substring(0, square.length() - length);
            } catch (Exception ex) {
                System.out.println(ex.getCause());
                break;
            }

            if ((new Integer(firstPart) + (new Integer(secondPart))) == p) {
                result.add(String.valueOf(p));
            }
        }

            if (result.size() == 0) {
                System.out.println("INVALID RANGE");
            } else {
                System.out.println(String.join(" ", result));
            }



    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        kaprekarNumbers(p, q); new HashMap<Character, Integer>().merge('k', 1, (k, v) -> (v + 1));

        scanner.close();
    }
}

package ru.dgaribov.yandexcode.contesttraining.anagrams;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lineOne = scanner.nextLine();
        String lineTwo = scanner.nextLine();

        if (lineOne.length() != lineTwo.length()) {
            System.out.println("0");
            return;
        }
        int[] ar1 = buildCharAr(lineOne);
        int[] ar2 = buildCharAr(lineTwo);

        for (int i = 0; i < ar1.length; i++) {
            if (ar1[i] != ar2[i]) {
                System.out.println("0");
                return;
            }
        }
        System.out.println("1");
    }

    private static int[] buildCharAr(String str) {
        int[] result = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 97;
            result[index]++;
        }
        return result;
    }
}

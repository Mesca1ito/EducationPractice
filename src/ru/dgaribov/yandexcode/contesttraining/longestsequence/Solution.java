package ru.dgaribov.yandexcode.contesttraining.longestsequence;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfLines = Integer.parseInt(scanner.nextLine());

        int longestSeq = 0;
        int firstOccurence = -1;
        for (int i = 0; i < numberOfLines; i++) {
            int symbol = Integer.parseInt(scanner.nextLine());
            if (symbol == 1) {
                if (firstOccurence == -1) firstOccurence = i;
                int currentSeq = 1 + i - firstOccurence;
                longestSeq = Math.max(currentSeq, longestSeq);
            } else {
                firstOccurence = -1;
            }
        }

        System.out.println(longestSeq);
    }
}

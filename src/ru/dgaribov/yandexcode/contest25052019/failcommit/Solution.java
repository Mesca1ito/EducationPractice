package ru.dgaribov.yandexcode.contest25052019.failcommit;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int commitsAmount = Integer.parseInt(scanner.nextLine());

        int left = 1;
        int right = commitsAmount;

        while (left <= right) {
            int middle = (left + right) / 2;
            String question = String.valueOf(middle);
            if (middle > 1) question = "" + (middle - 1) + " " + question;
            if (middle < commitsAmount) question = question + " " + (middle + 1);

            System.out.println(question);
            System.out.flush();

            String answer = scanner.nextLine();
            String[] answerArr = answer.split(" ");

            if (answerArr[0].equals("0")) {
                if (left == right) {
                    System.out.println("! " + left);
                    System.out.flush();
                    return;
                }
                right = middle - 2;
                continue;
            }

            for (int i = 1; i < answerArr.length; i++) {
                if (answerArr[i].equals("0")) {
                    System.out.println("! " + answerArr[i]);
                    System.out.flush();
                    return;
                }
            }

            left = middle + 2;
        }

        System.out.println("! " + left);
    }
}


package ru.dgaribov.yandexcode.contesttraining.duplicatesremoval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int numberOfLines = Integer.parseInt(scanner.nextLine());
//        if (numberOfLines < 1) return;
//
//        int previousNum = scanner.nextInt();
//        System.out.println(previousNum);
//        int nextInt;
//
//        for (int i = 1; i < numberOfLines; i++) {
//            try {
//                nextInt = scanner.nextInt();
//            } catch (Exception ex) {
//                continue;
//            }
//            if (nextInt == previousNum) continue;
//            System.out.println(nextInt);
//            previousNum = nextInt;
//        }


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"), 5);

            int numberOfLines = Integer.parseInt(reader.readLine());
            if (numberOfLines < 1) return;

            int previous = Integer.parseInt(reader.readLine());
            int current;
            System.out.println(previous);
            for (int i = 1; i < numberOfLines; i++) {
                current = Integer.parseInt(reader.readLine());
                if (current != previous) {
                    System.out.println(current);
                    previous = current;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

            new Integer("");
        }

    }
}

package ru.dgaribov.hackerrank.algorithms.strings.strongpassword;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        // Return the minimum number of characters to make the password strong

        String numbers = "0123456789";
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special_characters = "!@#$%^&*()-+";

        int numbersChars = 0;
        int upperCaseChars = 0;
        int lowerCaseChars = 0;
        int specialChars = 0;

        for (char theChar : password.toCharArray()) {
            if (numbers.indexOf(theChar) != -1) {
                numbersChars += 1;
            } else if (lower_case.indexOf(theChar) != -1) {
                lowerCaseChars += 1;
            } else if (upper_case.indexOf(theChar) != -1) {
                upperCaseChars += 1;
            } else if (special_characters.indexOf(theChar) != -1) {
                specialChars += 1;
            }
        }


        int minNumber = 0;
        if (numbersChars == 0) minNumber += 1;
        if (upperCaseChars == 0) minNumber += 1;
        if (lowerCaseChars == 0) minNumber += 1;
        if (specialChars == 0) minNumber += 1;

        if ((password.length() + minNumber) >= 6) return minNumber;
        else {
            return 6 - password.length();
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String password = scanner.nextLine();

        int answer = minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

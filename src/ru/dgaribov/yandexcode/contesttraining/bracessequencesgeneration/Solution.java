package ru.dgaribov.yandexcode.contesttraining.bracessequencesgeneration;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfBraces = Integer.parseInt(scanner.nextLine());
        parens(numOfBraces);

    }

    private static Set<String> getBraces(int num) {
        if (num < 1) return new HashSet<>();
        if (num == 1) {
            Set<String> result = new HashSet<>();
            result.add("()");
            return result;
        }

        Set<String> result = new LinkedHashSet<>();
        for (String subResult : getBraces(num - 1)) {

            result.add("(" + subResult + ")");
            result.add(subResult + "()");
            result.add("()" + subResult);
        }

        return result;
    }


    private static void parens(int count) {
        if (count < 1) return;
        char[] str = new char[count * 2];
        addParen(count, count, str, 0);
    }

    private static void addParen(int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) return;
        if (leftRem == 0 && rightRem == 0) {
            System.out.println(str);
            return;
        }

        str[index] = '(';
        addParen(leftRem - 1, rightRem, str, index + 1);
        str[index] = ')';
        addParen(leftRem, rightRem - 1, str, index + 1);
    }

}


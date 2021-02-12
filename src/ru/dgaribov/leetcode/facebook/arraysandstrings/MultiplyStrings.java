package ru.dgaribov.leetcode.facebook.arraysandstrings;

/**
 * @author David Garibov
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings app = new MultiplyStrings();
        String result = app.multiply("3", "6");
        System.out.println(result);
    }
    public String multiply(String num1, String num2) {
        int result = 0;
        for (int i = 0; i < num2.length(); i++) {
            for (int j = 0; j < num1.length(); j++) {
                result += getValueAtIndex(num2, i) * getValueAtIndex(num1, j);
            }

        }
        return String.valueOf(result);
    }


    int getValueAtIndex(String str, int i) {
        int multiplier = pow(10, i);
        char ch = str.charAt(str.length() - 1 - i);
        return Character.getNumericValue(ch) * multiplier;
    }


    int pow(int value, int powValue) {
        int result = 1;
        for (int i = 1; i <= powValue; i++) {
            result *= value;
        }
        return result;
    }
}

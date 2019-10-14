package ru.dgaribov.leetcode.zigzagconvertion;

public class Solution {
    public static String convert(String s, int numRows) {

        if (s == null || s.isEmpty() || numRows < 1) {
            return "";
        }

        boolean drawDown = true;
        String[][] result = new String[numRows][s.length()];
        for (int i = 0, j = 0, charIndex = 0; charIndex < s.length(); charIndex++) {

            try {
                result[i][j] = s.substring(charIndex, charIndex + 1);
            } catch (Exception e) {
                throw e;
            }
            if (drawDown) {
                if (i < numRows - 1) {
                    i++;
                } else {
                    if (i > 0) {
                        i--;
                    }
                    j++;
                    drawDown = false;
                }
            } else {

                if (i > 0) {
                    i--;
                    j++;
                } else {
                    if (numRows == 1) {
                        j++;
                    }
                    else {
                        i++;
                    }
                    drawDown = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String[] aResult : result) {
            for (String anAResult : aResult) {
                if (anAResult != null) sb.append(anAResult);
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(convert("ABC", 1));
    }
}
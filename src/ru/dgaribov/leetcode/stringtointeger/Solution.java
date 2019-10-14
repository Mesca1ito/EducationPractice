package ru.dgaribov.leetcode.stringtointeger;

class Solution {
    public static int myAtoi(String str) {
        if (str == null || str.trim().isEmpty()) return 0;
        str = str.trim();
        char[] charArr = str.toCharArray();
        if (!(Character.isDigit(charArr[0])
                || charArr[0] == '-'
                || charArr[1] == '+')) return 0;

        int result = 0;
        int offset = 0;
        if (charArr[0] == '-' || charArr[0] == '+') offset = 1;

        for (int i = offset; i < str.length(); i++) {
            int nextNum = Character.getNumericValue(charArr[i]);
            if (!Character.isDigit(charArr[i])) break;
            if (result > Integer.MAX_VALUE/10
                    || (result == Integer.MAX_VALUE/10 && nextNum > 7)) {
                if (charArr[0] == '-') result = Integer.MIN_VALUE;
                else result = Integer.MAX_VALUE;
                break;
            }
            result = result * 10 + nextNum;
        }

        if (charArr[0] == '-') result *= -1;

        return result;

    }

    public static void main(String[] args) {
        System.out.println(myAtoi("1"));
    }
}
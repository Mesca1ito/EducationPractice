package ru.dgaribov.leetcode.reverseinteger;

class Solution {
    public static int reverse(int x) {
        if (x < 10 && x > -10) return x;
        String str = new Integer(x).toString();
        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder("");
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '-') continue;
            if (chars[i] == '0' && result.toString().equals("")) continue;
            result.append(chars[i]);
        }

        Integer theResult;
        try {
            theResult = Integer.parseInt(result.toString());
        } catch (Exception ex) {
            return 0;
        }
        if (x < 0) theResult *= -1;

        return theResult;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public int reverse2(int x) {

        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < - 8)) return 0;

            rev = rev * 10 + pop;
        }

        return rev;
    }
}
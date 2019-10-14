package ru.dgaribov.hackerrank.java.javalambdaexpressions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


interface PerformOperation {
    boolean check(int a);
}


class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    PerformOperation isOdd() {
        return integer -> integer % 2 != 0;
    }

    PerformOperation isPrime() {
        return integer -> {
            if (integer > 0 && integer < 4) return true;
            for (int i = 2; i <= integer / 2; i++) {
                if (integer % i == 0) return false;
            }
            return true;
        };
    }

    PerformOperation isPalindrome() {
        return integer -> {
            String str = String.valueOf(integer);
            char[] charArr = str.toCharArray();
            int left = 0;
            int right = charArr.length - 1;

            while (right > left) {
                if (charArr[left++] != charArr[right--]) return false;
            }
            return true;
        };
    }
}

public class Solution {


    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}

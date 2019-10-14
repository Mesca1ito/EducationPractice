package ru.dgaribov.hackerrank.javapracrice.java1darray;

import java.util.Scanner;

public class Solution {

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        int maxI = 0;
        for (int i = 0; ; ) {

            if (i > maxI) maxI = i;
            if (i == maxI) {
                if (i >= game.length) return true;
                if (i + leap >= game.length || game[i + leap] == 0) {
                    i += leap;
                    continue;
                }
                if (game[i + 1] == 0) {
                    i += 1;
                } else {
                    if (i > 0 && game[i - 1] == 0) i -= 1;
                    else return false;
                }
            } else {
                if (i == 0 || i + leap <= maxI) return false;
                if (game[i + leap] == 0) i += leap;
                else if (game[i - 1] == 0) i += -1;
                else return false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }
}


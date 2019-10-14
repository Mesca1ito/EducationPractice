package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

public class Coins {
    private int makeChange(int n) {
        int[] denoms = {25, 10, 5 ,1};
        int[][] map = new int[n + 1][denoms.length];
        return makeChange(n, denoms, 0, map);

    }

    private int makeChange(int amount, int[] denoms, int index, int[][] map) {
        if (map[amount][index] > 0) {
            return map[amount][index];
        }


        if (index >= denoms.length - 1) return 1;
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount < amount; i++) {
            ways += makeChange(amount - i * denomAmount, denoms, index + 1, map);
        }

        map[amount][index] = ways;
        return ways;

    }

    public static void main(String[] args) {
        System.out.println(new Coins().makeChange(90));
    }
}

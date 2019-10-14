package ru.dgaribov.googlecode;

public class Solution {

    static int bestDeal(int[] prices) {
        int[] deals = new int[prices.length];
        int minPrice = -1;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (minPrice == -1 || price < minPrice) minPrice = price;
            deals[i] = ((prices[i] - minPrice) > deals[i-1] ? prices[i] - minPrice : deals[i-1]);
        }

        return deals[deals.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(bestDeal(new int[] {4, 1, 3, 6, 9, 8}));
    }
}



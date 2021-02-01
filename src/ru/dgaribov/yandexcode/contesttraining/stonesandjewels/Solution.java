package ru.dgaribov.yandexcode.contesttraining.stonesandjewels;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Boolean> jewelMap = new HashMap<>();

        int result = 0;

        String jewels = scanner.nextLine();
        String stones = scanner.nextLine();

        if (jewels.isEmpty() || stones.isEmpty()) {
            System.out.println(result);
            return;
        }

        for (char jewel: jewels.toCharArray()) {
            jewelMap.putIfAbsent(jewel, true);
        }


        for (char stone: stones.toCharArray()) {
            if (jewelMap.containsKey(stone)) result++;
        }

        System.out.println(result);
    }
}

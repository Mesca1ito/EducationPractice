package ru.dgaribov.yandexcode.contest25052019.interestinggame;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int K = Integer.parseInt(line.split(" ")[0]);
        int N = Integer.parseInt(line.split(" ")[0]);

        Set<Integer> numbers = new HashSet<>();
        line = scanner.nextLine();
        int biggestInt = 0;


        int vasya = 0;
        int petya = 0;
        for (String intStr : line.split(" ")) {
            int nextInt = Integer.parseInt(intStr);

            if (nextInt % 6 == 0 && nextInt % 9 == 0) {
                vasya = vasya > 0 ? vasya - 1 : vasya;
                petya = petya > 0 ? petya - 1 : petya;
                continue;
            }

            if (nextInt % 6 == 0) {
                vasya++;
            }
            if (nextInt % 9 == 0) {
                petya++;
            }
            if (vasya >= K) {
                System.out.println("Vasya");
                return;
            }
            if (petya >= N) {
                System.out.println("Petya");
                return;
            }

        }



        if (vasya == petya) {
            System.out.println("Draw");
            return;
        }

        System.out.println(vasya > petya ? "Vasya" : "Petya");


//        System.out.println(new ru.dgaribov.relex.Solution().nameTheWinner(numbers, biggestInt));
    }


    private String nameTheWinner(Set<Integer> numbers, int biggestNumber) {

        int six = 6;
        int nine = 9;


        int vasya = getAmount(numbers, six, biggestNumber);
        int petya = getAmount(numbers, nine, biggestNumber);

        if (vasya > petya) return "Vasya";
        if (petya > vasya) return "Petya";

        return "Draw";
    }

    int getAmount(Set<Integer> numbers, int base, int biggestNumber) {
        int result = 0;
        for (int i = 1; ; i++) {
            int check = base * i;
            if (check > biggestNumber) {
                break;
            }
            if (numbers.contains(check)) result++;
        }

        return result;
    }


}

package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * @author David Garibov
 */
public class NextClosestTime {
    public static void main(String[] args) {
        NextClosestTime app = new NextClosestTime();
        System.out.println(app.nextClosestTime("01:32"));
    }
    public String nextClosestTime(String time) {
        Set<Character> digitsSet = getDigitsSet(time);
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));
        return nextTime(hours, minutes, digitsSet);
    }

    private String nextTime(int hours, int minutes, Set<Character> digitsSet) {
        do {
            if (++minutes == 60) minutes = 0;
            if (minutes == 0 && ++hours == 24) hours = 0;

        } while (!timeIsValid(hours, minutes, digitsSet));
        return stringify(hours) + ":" + stringify(minutes);
    }

    private boolean timeIsValid(int hours, int minutes, Set<Character> digitsSet) {
        String hoursStr = stringify(hours);
        String minutesStr = stringify(minutes);
        return digitsSet.contains(hoursStr.charAt(0))
                && digitsSet.contains(hoursStr.charAt(1))
                && digitsSet.contains(minutesStr.charAt(0))
                && digitsSet.contains(minutesStr.charAt(1));
    }

    private String stringify(int digit) {
        return (digit < 10 ? "0" : "") + digit;
    }


    private Set<Character> getDigitsSet(String time) {
        Set<Character> result = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            char ch = time.charAt(i);
            if (ch != ':') {
                result.add(ch);
            }
        }

        return result;
    }
}

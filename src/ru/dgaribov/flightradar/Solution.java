package ru.dgaribov.flightradar;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    /*
     * Complete the function below.
     */

    static void OutputMostPopularDestination(int count, Scanner in) {
        Map<String, Integer> citiesMap = new HashMap<>(count);
        for (int i = 0; i < count; i++) {
            String city = in.nextLine();
            citiesMap.merge(city, 1, (a, b)-> a + b);
        }
        String theCity = citiesMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println(theCity);
    }




    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputMostPopularDestination(_count, in);
    }
}

package ru.dgaribov.java8practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        mapOperations();


    }

    private static void mapOperations() {
        // map: forEach
        Map<String, String> map = new HashMap<>();
        map.put("first key", "first value");
        map.put("second key", "second value");
        map.forEach((a, b) -> {
            System.out.println("key is " + a + " value is " + b);
        });

        // map: compute
        map.compute("first key", (a, b) -> "something new");
        map.forEach((a, b) -> {
            System.out.println("key is " + a + " value is " + b);
        });

        // map: computeIfAbsent
        map.putIfAbsent("first key", "something else");
        map.putIfAbsent("third key", "something else");
        map.forEach((a, b) -> {
            System.out.println("key is " + a + " value is " + b);
        });

        // map: computeIfPresent
        map.computeIfPresent("first key", (a,b) -> "if present");
        map.computeIfPresent("forth key", (a,b) -> "if present");
        map.forEach((a, b) -> {
            System.out.println("key is " + a + " value is " + b);
        });

        // map: getOrDefault
    }


    private static void listOperations() {
        // list: forEach
        List<String> firstList = new ArrayList<>();
        firstList.add("abc");
        firstList.add("abcd");
        firstList.add("abcde");
        List<char[]> listOfCharArrs = firstList.stream().map(a -> a.toCharArray()).collect(Collectors.toList());
        listOfCharArrs.forEach(chars -> {
            System.out.println(chars.length);
        });

        // list: removeIf
        listOfCharArrs.removeIf(a -> a.length == 3);
        listOfCharArrs.forEach(chars -> {
            System.out.println(chars.length);
        });
    }
}

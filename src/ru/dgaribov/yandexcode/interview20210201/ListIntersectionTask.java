package ru.dgaribov.yandexcode.interview20210201;

import java.util.*;

/**
 * @author David Garibov
 */
public class ListIntersectionTask {
//    Даны 2 списка. Нужно найти их пересечение, т.е. множество элементов, которые есть в обоих списках.
//    список == массив


    List<String> intersection(List<String> first, List<String> second) {
        if (first == null || second == null) return null;
        if (first.size() * second.size() == 0) return null;

        Set<String> theSet = new HashSet<>();

        List<String> longest = first.size() >= second.size() ? first : second;
        List<String> shortest = longest == first ? second: first;

        for (String el: longest) {
            theSet.add(el);
        }

        List<String> result = new ArrayList<>();
        for (String el: shortest) {
            if (theSet.contains(el)) {
                result.add(el);
            }
        }

        return result;
    }




    List<String> intersection2(List<String> first, List<String> second) {
        if (first == null || second == null) return null;
        if (first.size() * second.size() == 0) return null;

        List<String> longest = first.size() >= second.size() ? first : second;
        List<String> shortest = longest == first ? second: first;

        Map<String, Integer> theMap = new HashMap<>();
        for (String el: shortest) {
            Integer val = theMap.get(el);
            if (val == null) val = 1;
            else {
                val +=1;
            }
            theMap.put(el, val);
        }

        List<String> result = new ArrayList<>();
        for (String el: longest) {
            Integer num = theMap.get(el);
            if (num == null || num == 0) {
                continue;
            } else {

            }
        }

        return result;
    }


//
//1 2 3 4 5 6
//        1 3 6
//
//        1 3 6
//
//
//
//        1 3 6
//        1 2 3 4 5 6
//
//        1 3 6
//
//
//    {}
//1 2 3
//
//        null
//
//
//        1 1 3 6
//
//        1 1 3 3 6 6
//
//        1 1 3 6
//

}

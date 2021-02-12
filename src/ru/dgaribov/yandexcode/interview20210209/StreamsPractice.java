package ru.dgaribov.yandexcode.interview20210209;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Garibov
 */
public class StreamsPractice {
    public static void main(String[] args) {
        List<List<Integer>> myList = new LinkedList<>();
        myList.add(Arrays.asList(1, 2 ,3));
        myList.add(Arrays.asList(4, 5 ,6));
        myList.add(Arrays.asList(7, 8 ,9));

        String result = myList.stream().flatMap(Collection::stream).map(el -> "" + el).collect(Collectors.joining(","));
        System.out.println(result);
    }
}

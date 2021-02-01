package ru.dgaribov.yandexcode.interview20210201;

/**
 * @author David Garibov
 */
public class LinkedListTask {
//    Есть односвязный
//    список,
//    заданный указателем
//    на его
//    начало.Нужно написать
//    функцию,
//    которая возвращает
//    N-
//    ый с
//    конца элемент.

    Node findNthFromEnd(Node start, int n) {

        if (start == null) return null;

        Node right = start;
        Node left = start;

        int counter = 0;
        while (right.next != null) {
            right = right.next;
            if (counter < n) {
                counter++;
            } else {
                left = left.next;
            }
        }

        if (counter < n) return null;

        return left;
    }

    class Node {
        int val;
        Node next;
    }

}
//
//
//1, 2, 3 ,4 ,5, 6, 7
//
//        n = 3
//
//        output = 4
//
//
//
//        1, 2
//
//        n = 3
//
//        output = Exception
//
//
//        {}
//
//        n = 3
//
//        output = Exception
//
//
//        1, 2, 3 ,4 ,5, 6, 7
//
//        n = 0
//
//        output = 7
//
//
//        1, 2, 3 ,4 ,5, 6, 7
//
//        n = 6
//
//        output = 1
//
//
//        1
//
//        n = 0
//
//        output = 1
//
//
//        1
//
//        n = 0
//
//        output = 1
//        }

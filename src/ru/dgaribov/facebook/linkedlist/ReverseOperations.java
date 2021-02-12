package ru.dgaribov.facebook.linkedlist;

import java.util.Stack;

/**
 * @author David Garibov
 */
public class ReverseOperations {

    public static void main(String[] args) {
        ReverseOperations reverseOperations = new ReverseOperations();
        int[] arr_1 = {2, 18, 24, 3, 5, 7, 9, 6, 12};
        Node result = reverseOperations.reverse(reverseOperations.createLinkedList(arr_1));
        Node expected = reverseOperations.createLinkedList(new int[]{24, 18, 2, 3, 5, 7, 9, 12, 6});
        reverseOperations.check(expected, result);
    }

    class Node {
        int data;
        Node next;
        Node(int x) {
            data = x;
            next = null;
        }
    }

    // Add any helper functions you may need here
    Node reverseStack(Stack<Node> stack) {
        Node start = null;
        Node current = null;
        while (!stack.isEmpty()) {
            Node el = stack.pop();
            if (start == null) start = el;
            if (current == null) current = start;
            else {
                current.next = el;
                current = current.next;
                if (stack.isEmpty()) current.next = null;
            }
        }
        return start;
    }

    Node reverse(Node head) {
        // Write your code here
        Node current = head;
        Node prev = null;
        Stack<Node> evenStack = new Stack<>();
        while (current != null) {

            while (current != null && current.data % 2 == 0) {
                evenStack.push(current);
                current = current.next;
            }
            if (!evenStack.isEmpty()) {
                Node reversed = reverseStack(evenStack);
                if (prev == null) {
                    head = reversed;
                    prev = head;
                } else {
                    prev.next = reversed;
                }
                while (prev.next != null) {
                    prev = prev.next;
                }
                prev.next = current;
            }


            prev = current;
            if (current != null) current = current.next;
        }

        return head;

    }

    Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = head;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }


    void check(Node expectedHead, Node outputHead) {
        boolean result = true;
        Node tempExpectedHead = expectedHead;
        Node tempOutputHead = outputHead;
        while (expectedHead != null && outputHead != null) {
            result &= (expectedHead.data == outputHead.data);
            expectedHead = expectedHead.next;
            outputHead = outputHead.next;
        }
        if (!(expectedHead == null && outputHead == null)) result = false;

        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" );
        } else {
            System.out.print(wrongTick + " Test #" + ": Expected ");
            printLinkedList(tempExpectedHead);
            System.out.print(" Your output: ");
            printLinkedList(tempOutputHead);
            System.out.println();
        }
    }

    void printLinkedList(Node head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null)
                System.out.print(" ");
        }
        System.out.print("]");
    }
}

package ru.dgaribov.crackingthecodinginterview.linkedlists;

import java.util.Stack;

public class Palindrome {

    public boolean isPalindromeRecurse(LinkedListNode head) {
        int listSize = sizeOfList(head);
        return isPalindromeRec(head, listSize).result;
    }

    private static Result isPalindromeRec(LinkedListNode head, int length) {
        if (head == null || length <= 0) {
            return new Result(head, true);
        } else if (length == 1) {
            return new Result(head.next, true);
        }


        Result res = isPalindromeRec(head.next, length - 2);
        if (!res.result || res.node == null) return res;

        res.result = head.data == res.node.data;
        res.node = res.node.next;

        return res;
    }

    private static int sizeOfList(LinkedListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private static class Result {
        LinkedListNode node;
        boolean result;

        public Result(LinkedListNode node, boolean result) {
            this.node = node;
            this.result = result;
        }
    }


    public boolean isPalindromeUsingStack(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.data != stack.pop()) return false;
            slow = slow.next;
        }

        return true;
    }

    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    private static LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    private static boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) return false;
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }
}

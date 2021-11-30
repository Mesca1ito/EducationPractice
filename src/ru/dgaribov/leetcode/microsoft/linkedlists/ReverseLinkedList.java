package ru.dgaribov.leetcode.microsoft.linkedlists;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * @author David Garibov
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        head = reverseListRec(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    public static ListNode reverseListRec(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}




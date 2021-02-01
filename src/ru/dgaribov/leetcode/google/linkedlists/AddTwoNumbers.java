package ru.dgaribov.leetcode.google.linkedlists;

/**
 * @author David Garibov
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode current = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int newVal = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            ListNode newNode = new ListNode(newVal);
            if (current == null) {
                current = newNode;
                result = current;
            } else {
                current.next = newNode;
                current = newNode;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return result;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

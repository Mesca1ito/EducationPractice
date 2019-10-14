package ru.dgaribov.leetcode.addtwonumbers;

import java.io.Serializable;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode result = resultHead;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }


            result.next = new ListNode(carry % 10);
            carry = carry / 10;
            result = result.next;


        }

        return resultHead.next;

    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers(node1, node2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}


class ListNode implements Serializable {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


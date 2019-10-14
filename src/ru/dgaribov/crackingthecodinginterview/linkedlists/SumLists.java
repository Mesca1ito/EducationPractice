package ru.dgaribov.crackingthecodinginterview.linkedlists;

public class SumLists {
    public static LinkedListNode addListsBackwarded(LinkedListNode n1, LinkedListNode n2, int carry) {
        if (n1 == null && n2 == null && carry == 0) {
            return null;
        }

        LinkedListNode result = new LinkedListNode();

        int value = carry;
        if (n1 != null) {
            value += n1.data;
        }

        if (n2 != null) {
            value += n2.data;
        }

        result.data = value % 10;

        if (n1 != null || n2 != null) {
            result.next = addListsBackwarded(n1 == null ? null : n1.next, n2 == null ? null : n2.next, carry > 10 ? 1 : 0);
        }

        return result;
    }


    public static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        if (len1 < len2) {
            padList(l1, len2 - len1);
        } else {
            padList(l2, len1 - len2);
        }


        PartialSum sum = addListsHelper(l1, l2);

        if (sum.carry == 0) {
            return sum.sum;
        }

        return insertBefore(sum.sum, sum.carry);
    }


    private static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            return new PartialSum();
        }

        PartialSum partialSum = addListsHelper(l1 == null ? null : l1.next, l2.next);
        int val = partialSum.carry + (l1 == null ? 0 : l1.data) + l2.data;
        partialSum.sum = insertBefore(partialSum.sum, val % 10);
        partialSum.carry = val / 10;

        return partialSum;
    }

    private static int length(LinkedListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }

        return length;
    }

    private static LinkedListNode padList(LinkedListNode node, int padding) {

        for (int i = 0; i < padding; i++) {
            node = insertBefore(node, 0);
        }
        return node;
    }

    private static LinkedListNode insertBefore(LinkedListNode node, int data) {
        if (node == null) return null;
        LinkedListNode head = new LinkedListNode(data);
        head.next = node;
        return head;
    }
}


class PartialSum {
    public LinkedListNode sum = null;
    public int carry = 0;
}

package ru.dgaribov.crackingthecodinginterview.linkedlists;

public class KthFromLast {
    public static int printKthToLast(LinkedListNode head, int k) {
        if (head == null) {
            return 0;
        }
        int index = 1 + printKthToLast(head.next, k);
        if (index == k) {
            System.out.println(head.data);
        }
        return index;
    }

    public static LinkedListNode kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    public static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) {
            return null;
        }

        LinkedListNode node = kthToLast(head.next, k, idx);

        idx.value += 1;
        if (idx.value == k) {
            return head;
        }

        return node;
    }

    public static LinkedListNode kthToLastIterative(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        for (int i = 0; i < k; i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}


class Index {
    public int value;
}

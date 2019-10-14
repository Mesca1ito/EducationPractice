package ru.dgaribov.crackingthecodinginterview.linkedlists;

public class Partition {
    public static LinkedListNode partition(LinkedListNode linkedListNode, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        while (linkedListNode != null) {
            LinkedListNode next = linkedListNode.next;
            if (linkedListNode.data < x) {
                if (beforeStart == null) {
                    beforeStart = linkedListNode;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = linkedListNode;
                    beforeEnd = linkedListNode;
                }
            } else {
                if (afterStart == null) {
                    afterStart = linkedListNode;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = linkedListNode;
                    afterEnd = linkedListNode;
                }
            }

            linkedListNode = next;
        }

        if (beforeStart == null) return beforeStart;
        beforeEnd.next = afterStart;
        return beforeStart;
    }

    public static LinkedListNode partitionBrokenOrder(LinkedListNode linkedListNode, int x) {
        LinkedListNode head = linkedListNode;
        LinkedListNode tail = linkedListNode;

        while (linkedListNode != null) {
            LinkedListNode next = linkedListNode.next;
            if (linkedListNode.data < x) {
                linkedListNode.next = head;
                head = linkedListNode;
            } else {
                tail.next = linkedListNode;
                tail = linkedListNode;
            }
            linkedListNode = next;
        }

        tail.next = null;

        return head;
    }
}

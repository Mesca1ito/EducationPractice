package ru.dgaribov.crackingthecodinginterview.linkedlists;

public class Intersection {
    public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == null) return null;
        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);
        if (result1.tail != result2.tail) return null;
        LinkedListNode shorter = result1.size >= result2.size ? list2 : list1;
        LinkedListNode longer = result1.size >= result2.size ? list1 : list2;
        longer  = getKthNode(longer, Math.abs(result1.size - result2.size));
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        return longer;
    }

    private static Result getTailAndSize(LinkedListNode list) {
        int size = 0;
        LinkedListNode tail = null;
        while (list != null) {
            size++;
            if (list.next == null) {
                tail = list;
            }
            list = list.next;
        }

        return new Result(size, tail);
    }

    private static LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

    private static class Result {
        int size;
        LinkedListNode tail;

        public Result(int size, LinkedListNode tail) {
            this.size = size;
            this.tail = tail;
        }
    }
}

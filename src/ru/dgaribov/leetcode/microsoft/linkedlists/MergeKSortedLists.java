package ru.dgaribov.leetcode.microsoft.linkedlists;

/**
 * @author David Garibov
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        int size = lists.length;
        while (size > 1) {

            int pointer = 0;
            for (int i = 0; i < size; i += 2) {
                lists[pointer++] = mergeTwoLists(lists[i], i + 1 == size ? null : lists[i + 1]);
            }
            size = (size / 2) + (size % 2);
        }
        return lists[0];
    }


    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode pointer = head;


        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    Pair pair = addNext(head, pointer, list1);
                    head = pair.head;
                    pointer = pair.pointer;
                    list1 = list1.next;
                } else {
                    Pair pair = addNext(head, pointer, list2);
                    head = pair.head;
                    pointer = pair.pointer;
                    list2 = list2.next;
                }
            } else {
                {
                    if (list1 != null) {
                        Pair pair = addNext(head, pointer, list1);
                        head = pair.head;
                        pointer = pair.pointer;
                        list1 = list1.next;
                    } else {
                        Pair pair = addNext(head, pointer, list2);
                        head = pair.head;
                        pointer = pair.pointer;
                        list2 = list2.next;
                    }

                }
            }
        }

        return head;
    }


    private Pair addNext(ListNode head, ListNode pointer, ListNode next) {
        if (head == null) {
            head = next;
            pointer = head;
        } else {
            pointer.next = next;
            pointer = pointer.next;
        }

        return Pair.of(head, pointer);
    }

    static class Pair {
        ListNode head;
        ListNode pointer;

        static Pair of(ListNode head, ListNode pointer) {
            Pair pair = new Pair();
            pair.head = head;
            pair.pointer = pointer;
            return pair;
        }
    }
}


class ListNode {
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

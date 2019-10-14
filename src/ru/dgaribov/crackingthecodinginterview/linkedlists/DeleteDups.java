package ru.dgaribov.crackingthecodinginterview.linkedlists;

import java.util.HashSet;
import java.util.Set;

public class DeleteDups {
    public static void deleteDups(LinkedListNode n) {
        Set<Integer> set = new HashSet<>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    public static void deleteDupsNoBuffer(LinkedListNode head) {
        LinkedListNode current = head;

        while (current != null) {
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }
}

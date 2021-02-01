package ru.dgaribov.leetcode.google.linkedlists;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class CopyRandomList {
    Map<Node, Node> seen = new HashMap<>();

    public Node copyRandomList(Node head) {
        Node copyHead = null;
        Node current = null;


        while (head != null) {
            Node orig = head;
            Node copyNext = head.next != null ? seen.computeIfAbsent(head.next, x -> new Node(orig.next.val)) : null;
            Node copyRandom = head.random != null ? seen.computeIfAbsent(head.random, x -> new Node(orig.random.val)) : null;
            Node copy = seen.computeIfAbsent(head, x -> new Node(orig.val));
            copy.next = copyNext;
            copy.random = copyRandom;
            if (current == null) {
                current = copy;
                copyHead = current;
            } else {
                current.next = copy;
                current = copy;
            }
            head = head.next;
        }

        return copyHead;
    }

    public Node copyRandomListRecursive(Node head) {
        if (head == null) {
            return null;
        }
        Node copy = seen.get(head);
        if (copy != null) return copy;
        copy = new Node(head.val);
        seen.put(head, copy);
        copy.next = copyRandomListRecursive(head.next);
        copy.random = copyRandomListRecursive(head.random);
        return copy;
    }
}


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

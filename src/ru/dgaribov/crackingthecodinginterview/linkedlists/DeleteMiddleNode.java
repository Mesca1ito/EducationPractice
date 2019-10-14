package ru.dgaribov.crackingthecodinginterview.linkedlists;

public class DeleteMiddleNode {
    public static boolean deleteMiddleNode(LinkedListNode node) {
        if (node == null || node.next == null) return false;
        node.data = node.next.data;
        node.next = node.next.next;
        return true;
    }
}

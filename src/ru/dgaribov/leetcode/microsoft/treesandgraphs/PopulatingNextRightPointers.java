package ru.dgaribov.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * @author David Garibov
 */
public class PopulatingNextRightPointers {

    public Node connectIterative(Node root) {

        if (root == null) {
            return root;
        }

        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);

        // Outer while loop which iterates over
        // each level
        while (Q.size() > 0) {

            // Note the size of the queue
            int size = Q.size();

            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {

                // Pop a node from the front of the queue
                Node node = Q.poll();

                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }

                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }

        // Since the tree has now been modified, return the root node
        return root;
    }

    public Node connect(Node root) {
        Map<Integer, List<Node>> levelMap = new HashMap<>();
        connectNodesRec(root, levelMap, 1);
        return root;
    }

    public void connectNodesRec(Node node, Map<Integer, List<Node>> levelNodeMap, int level) {
        if (node == null) return;
        List<Node> levelNodes = levelNodeMap.computeIfAbsent(level, key -> new ArrayList<>());
        if (!levelNodes.isEmpty()) {
            Node prev = levelNodes.get(levelNodes.size() - 1);
            prev.right = node;
        }
        levelNodes.add(node);
        connectNodesRec(node.left, levelNodeMap, level+1);
        connectNodesRec(node.right, levelNodeMap, level+1);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}

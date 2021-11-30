package ru.dgaribov.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * @author David Garibov
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Integer, Node> visited = new HashMap<>();
        return cloneGraphRec(node, visited);
    }

    public Node cloneGraphRec(Node node, Map<Integer, Node> visited) {
        if (node == null) return node;
        if (visited.containsKey(node.val)) return visited.get(node.val);
        Node copy = new Node(node.val);
        visited.put(node.val, copy);
        for (Node neighbor: node.neighbors) {
            copy.neighbors.add(cloneGraphRec(neighbor, visited));
        }

        return copy;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

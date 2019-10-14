package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RouteBetweenNodes {





    boolean search(Graph g, Node start, Node end) {
        if (start == end) {
            return true;
        }

        Queue<Node> q = new LinkedList<>();
        g.getNodes().forEach(node -> node.state = State.Unvisited);
        start.state = State.Visiting;
        q.add(start);

        Node u;
        while (!q.isEmpty()) {
            u = q.poll();
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) return true;
                        v.state = State.Visiting;
                        q.add(v);
                    }
                }
                u.state = State.Visited;
            }
        }

        return false;
    }


    // Depth first search
    boolean dfs(Node start, Node end) {
        if (start == end) return true;
        for(Node node: start.getAdjacent()) {
            if (dfs(node, end)) return true;
        }
        return false;
    }
}

enum State {
    Unvisited, Visited, Visiting
}

class Graph {
    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }


}

class Node {
    State state;
    private List<Node> adjacentNodes;

    List<Node> getAdjacent() {
        return adjacentNodes;
    }
}
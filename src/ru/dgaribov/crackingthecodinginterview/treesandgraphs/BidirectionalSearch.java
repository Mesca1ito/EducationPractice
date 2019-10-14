package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

import java.util.*;

public class BidirectionalSearch {
    List<Person> findPathBiBFS(Map<Integer, Person> people, int source, int destination) {
        BFSData sourceData = new BFSData(people.get(source));
        BFSData destData = new BFSData(people.get(destination));

        while (!sourceData.isFinished() && !destData.isFinished()) {
            Person collision = searchLevel(people, sourceData, destData);
            if (collision != null) {
                return mergePaths(sourceData, destData, collision.id);
            }

            collision = searchLevel(people, destData, sourceData);
            if (collision != null) {
                return mergePaths(destData, sourceData, collision.id);
            }
        }

        return null;
    }


    private Person searchLevel(Map<Integer, Person> people, BFSData primary, BFSData secondary) {
        int count = primary.toVisit.size();
        for (int i = 0; i < count; i++) {
            PathNode pathNode = primary.toVisit.poll();
            int personId = pathNode.getPerson().id;

            if (secondary.visited.containsKey(personId)) return pathNode.getPerson();

            Person person = pathNode.getPerson();
            List<Integer> friends = person.getFriends();
            for (int friendId: friends) {
                if (primary.visited.containsKey(friendId)) continue;
                Person friend = people.get(friendId);
                PathNode next = new PathNode(friend, pathNode);
                primary.visited.put(friendId, next);
                primary.toVisit.add(next);
            }

        }

        return null;
    }

    private List<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connection) {
        PathNode end1 = bfs1.visited.get(connection);
        PathNode end2 = bfs2.visited.get(connection);
        LinkedList<Person> pathOne = end1.collapse(false);
        LinkedList<Person> pathTwo = end2.collapse(true);
        pathTwo.removeFirst();
        pathOne.addAll(pathTwo);
        return pathOne;
    }
}




class Person {
    int id;
    List<Integer> friends = new ArrayList<>();

    public List<Integer> getFriends() {
        return friends;
    }
}

class BFSData {
    public Queue<PathNode> toVisit = new LinkedList<>();
    public Map<Integer, PathNode> visited = new HashMap<>();

    public BFSData(Person root) {
        PathNode sourcePath = new PathNode(root, null);
        toVisit.add(sourcePath);
        visited.put(root.id, sourcePath);
    }

    public boolean isFinished() {
        return toVisit.isEmpty();
    }
}

class PathNode {
    private Person person = null;
    private PathNode previousNode = null;

    public PathNode(Person person, PathNode previousNode) {
        this.person = person;
        this.previousNode = previousNode;
    }

    public Person getPerson() {
        return person;
    }

    public LinkedList<Person> collapse(boolean startsWithRoot) {
        LinkedList<Person> path = new LinkedList<>();
        PathNode node = this;
        while (node != null) {
            if (startsWithRoot) {
                path.addLast(node.getPerson());
            } else {
                path.addFirst(node.getPerson());
            }

            node = node.previousNode;
        }

        return path;
    }
}
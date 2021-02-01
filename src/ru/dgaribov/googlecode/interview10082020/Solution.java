package ru.dgaribov.googlecode.interview10082020;

import java.util.*;

/**
 * Given a list of edges:
 * (1, 2) *
 * (2, 3)
 * (3, 4)
 * (2, 1) *
 * (4, 2)
 * <p>
 * <p>
 * (1, 2)	0
 * (3, 1)
 * (4, 1)
 * <p>
 * 0
 * <p>
 * Example:
 * (4, 5)
 * (4, 3)
 * (5, 4)
 * <p>
 * To find the number of pairs, that represent reciprocated edges.
 * (a, b) and (b, a) are reciprocated
 */
class ListOfEdges {
    int numberOfPairs(List<Edge> edges) {
        int result = 0;

        Map<Integer, Set<Integer>> theMap = new HashMap<>();
        for (Edge theEdge : edges) {
            Set<Integer> possibleCompliments = theMap.get(theEdge.y);

            if (possibleCompliments != null) {
                boolean complimentWasFound = possibleCompliments.contains(theEdge.x);

                if (!complimentWasFound) {

                    Set<Integer> listToPut = theMap.computeIfAbsent(theEdge.x, k -> new HashSet<>());
                    listToPut.add(theEdge.y);
                } else {
                    result++;
                }
            } else {
                Set<Integer> listToPut = theMap.computeIfAbsent(theEdge.x, k -> new HashSet<>());
                listToPut.add(theEdge.y);
            }

        }

        return result;
    }
}


class Edge {
    int x;
    int y;
}

package ru.dgaribov.yandexcode.interview20200806;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Garibov
 */
public class Solution2 {
    // "Kazak" -
// "Madam I'm Adam!"
//
    boolean isPalindrome(String str) {
        char[] ar = str.toCharArray();
        int left = 0;
        int right = ar.length - 1;
        while (left < right) {
            left = skipSimbols(ar, left, true);
            right = skipSimbols(ar, right, false);
            if (left >= right) return true;
            if (toUpperChar(ar[left]) != toUpperChar(ar[right])) return false;
            left++;
            right--;
        }

        return true;
    }


    int skipSimbols(char[] ar, int pos, boolean right) {
        int dest = right ? ar.length - 1 : 0;
        int step = right ? 1 : -1;

        return step;
    }


//     "asd' dsa'"
//
//             ""
//             "             "
//             "Kk"

    char toUpperChar(char a) {
        String str = String.valueOf(a);
        str = str.toUpperCase();
        return str.charAt(0);
    }
}


class EquivalentNodes {


    Pair<Node, Node> findEquivalents(Node root) {
//        Pair<Node, Node> result = new Pair<>();
//        findSubSet(root, new HashMap<Integer, Node>(), result);
//        return result;
        return null;
    }


    int findSubSet(Node node, Map<Integer, Node> theMap, Pair<Node, Node> result) {
//        if (node == null) return 0;
//        if (result.getFirst() != null) return -1;
//        int nodeVal = 1 << 'Z' - node.val;
//        nodeVal = nodeVal | findSubSet(node.left, theMap);
//        nodeVal = nodeVal | findSubSet(node.right, theMap);
//        if (theMap.contains(nodeVal)) {
//            result.setFirst(node);
//            result.setSecond(theMap.get(nodeVal));
//            return -1;
//        }
//        theMap.put(nodeVal, node);
//        return nodeVal;
        return 0;
    }
}


class Node {
    int value;
    Node left;
    Node right;
}



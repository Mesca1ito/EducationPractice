package ru.dgaribov.yandexcode.interview20210209;

/**
 * @author David Garibov
 */
/**
 * Дано бинарное дерево, нужно найти и вернуть элемент,
 * находящийся на максимальной глубине от корня.
 */
public class DeepestNode {

    public static class Node {
        int val;
        Node left;
        Node right;
    }

    int maxLevel = 0;
    Node node;

    public Node getDeepestNodeValue(Node root){
        //your code
        if (root == null) return null;
        deepestRecur(root, 1);
        return this.node;
    }

    private void deepestRecur(Node node, int level) {
        if (level > maxLevel) {
            maxLevel = level;
            this.node = node;
        } else {
            if (node.left != null) {
                deepestRecur(node.left, level + 1);
            }
            if (node.right != null) {
                deepestRecur(node.right, level + 1);
            }
        }
    }
}

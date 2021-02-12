package ru.dgaribov.lasso;

/**
 * Given a binary tree t, determine sum of all nodes on the level l
 *
 * @author David Garibov
 */
public class CalculateSumAtLevel {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        CalculateSumAtLevel app = new CalculateSumAtLevel();
        System.out.println(app.calculateSumAtLevel(root, 3));
    }


    public int calculateSumAtLevel(Node root, int level) {
        Sum sum = new Sum(0);
        calculateRecursive(root, level, 1, sum);
        return sum.val;
    }

    private void calculateRecursive(Node current, int level, int currentLevel, Sum sum) {
        if (level == currentLevel) {
            sum.val += current.val;
        } else {
            if (current.left != null) calculateRecursive(current.left, level, currentLevel + 1, sum);
            if (current.right != null) calculateRecursive(current.right, level, currentLevel + 1, sum);
        }
    }
}

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }
}

class Sum {
    int val;

    Sum(int val) {
        this.val = val;
    }
}

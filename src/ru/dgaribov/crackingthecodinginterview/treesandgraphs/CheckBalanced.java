package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

public class CheckBalanced {

    private int getHeight(TreeNode root) {
        if (root == null) return -1;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) return Integer.MIN_VALUE;
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != Integer.MIN_VALUE;
    }
}

package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

public class Successor {

    public TreeNode findSuccessor(TreeNode treeNode) {
        if (treeNode.right != null) {
            return leftmostChild(treeNode);
        } else {
            while (treeNode.parent != null) {
                if (treeNode.parent.right == treeNode) {
                    treeNode = treeNode.parent;
                } else {
                    return treeNode.parent;
                }
            }
            return null;
        }
    }


    private TreeNode leftmostChild(TreeNode node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

package ru.dgaribov.leetcode.microsoft.treesandgraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * @author David Garibov
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode p = new TreeNode(3);
        root.left = p;
        root.left.left = new TreeNode(2);
        TreeNode q = new TreeNode(1);
        root.left.left.left = q;

        TreeNode treeNode = lowestCommonAncestor(root, p, q);
        System.out.println(treeNode.val);
    }

    /**
     * Start traversing the tree from the root node.
     * If both the nodes p and q are in the right subtree, then continue the search with right subtree starting step 1.
     * If both the nodes p and q are in the left subtree, then continue the search with left subtree starting step 1.
     * If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's
     * subtrees. and hence we return this common node as the LCA.
     *
     * @param root
     * @param p
     * @param q
     *
     * @return
     */
    public TreeNode lowestCommonAncestorRec(TreeNode root, TreeNode p, TreeNode q) {

        // Value of current node or parent node.
        int parentVal = root.val;

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            return lowestCommonAncestorRec(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            return lowestCommonAncestorRec(root.left, p, q);
        } else {
            // We have found the split point, i.e. the LCA node.
            return root;
        }
    }

    /**
     * The steps taken are also similar to approach 1. The only difference is instead of recursively calling the
     * function, we traverse down the tree iteratively. This is possible without using a stack or recursion since we
     * don't need to backtrace to find the LCA node. In essence of it the problem is iterative, it just wants us to find
     * the split point. The point from where p and q won't be part of the same subtree or when one is the parent of the
     * other.
     *
     * @param root
     * @param p
     * @param q
     *
     * @return
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {

            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
    }

    static public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> pPath = buildPath(root, p);
        Queue<TreeNode> qPath = buildPath(root, q);
        Queue<TreeNode> shortPath = (pPath.size() < qPath.size()) ? pPath : qPath;
        Queue<TreeNode> longPath = shortPath == pPath ? qPath : pPath;
        int longPathSize = longPath.size();
        int shortPathSize = shortPath.size();
        for (int i = 0; i < longPathSize - shortPathSize; i++) {
            longPath.poll();
        }
        TreeNode node = null;
        while (!longPath.isEmpty() && (node = longPath.poll()) != shortPath.poll()) {

        }

        return node;
    }

    static private Queue<TreeNode> buildPath(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            Queue<TreeNode> path = new LinkedList<>();
            path.add(p);
            return path;
        }

        Queue<TreeNode> pathLeft = buildPath(root.left, p);
        Queue<TreeNode> pathRight = buildPath(root.right, p);
        Queue<TreeNode> path = null;
        if (pathLeft == null && pathRight == null) {
            return path;
        }
        if (pathLeft != null) {
            pathLeft.add(root);
            return pathLeft;
        } else {
            pathRight.add(root);
            return pathRight;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

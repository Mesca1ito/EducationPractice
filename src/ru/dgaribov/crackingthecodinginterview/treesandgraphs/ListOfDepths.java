package ru.dgaribov.crackingthecodinginterview.treesandgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOfDepths {


    // BFS
    public List<List<TreeNode>> createLevelListBFS(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        List<TreeNode> current = new LinkedList<>();
        if (root != null) current.add(root);
        while (current.size() > 0) {
            result.add(current); // Add previous level
            List<TreeNode> parents = current;
            current = new LinkedList<>();
            for (TreeNode parent : parents) {
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }

        return result;
    }


    // DFS
    public List<List<TreeNode>> createLevelList(TreeNode root) {
        List<List<TreeNode>> lists = new ArrayList<>();
        createLevelList(root, lists, 0);
        return lists;
    }


    private void createLevelList(TreeNode root, List<List<TreeNode>> lists, int level) {
        if (root == null) return;
        List<TreeNode> list = null;
        if (lists.size() == level) {
            list = new LinkedList<>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelList(root.left, lists, level + 1);
        createLevelList(root.right, lists, level + 1);
    }
}

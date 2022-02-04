package ru.dgaribov.leetcode.microsoft.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * @author David Garibov
 */
public class WordSearch2 {

    public static void main(String[] args) {
//        char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        char[][] board = new char[][]{{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
//        String[] words = new String[]{"oath","pea","eat","rain"};
        String[] words = new String[]{"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};
        List<String> result = findWords(board, words);
        System.out.println(result);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (String word : words) {
                    if (word.startsWith("" + board[i][j])
                            && backtrack(new HashSet<>(), board, i, j, new StringBuilder("" + board[i][j]), word)) {
                        result.add(word);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }


    private static boolean backtrack(Set<String> visited, char[][] board, int i, int j, StringBuilder sb, String word) {
        String cellCode = "" + i + "-" + j;
        if (visited.contains(cellCode)) {
            return false;
        }
        if (word.equals(sb.toString())) {
            return true;
        }
        visited.add(cellCode);
        if (board.length > i + 1 && word.startsWith(sb + "" + board[i + 1][j])) {
            return backtrack(visited, board, i + 1, j, sb.append(board[i + 1][j]), word);
        }
        if (0 < i && word.startsWith(sb + "" + board[i - 1][j])) {
            return backtrack(visited, board, i - 1, j, sb.append(board[i - 1][j]), word);
        }
        if (board[i].length > j + 1 && word.startsWith(sb + "" + board[i][j + 1])) {
            return backtrack(visited, board, i, j + 1, sb.append(board[i][j + 1]), word);
        }
        if (0 < j && word.startsWith(sb + "" + board[i][j - 1])) {
            return backtrack(visited, board, i, j - 1, sb.append(board[i][j - 1]), word);
        }
        return false;
    }
}


class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    String word = null;

    public TrieNode() {
    }
}

class Solution {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}


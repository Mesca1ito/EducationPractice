package ru.dgaribov.leetcode.google;

/**
 * @author David Garibov
 */
public class TotalFruit {
    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}));
    }

    public static int totalFruit(int[] tree) {
        int sum = 0;
        int max = 0;
        Integer firstType = null;
        Integer secondType = null;
        int seqStartIndex = 0;
        for (int i = 0; i < tree.length; i++) {

            if (firstType == null) {
                firstType = tree[i];
            } else if (secondType == null) {
                if (tree[i] != firstType) secondType = tree[i];
            } else {
                if (tree[i] != secondType && tree[i] != firstType) {
                    i = seqStartIndex - 1;
                    sum = 0;
                    firstType = null;
                    secondType = null;
                    continue;
                }
            }

            if (i > 0 && tree[i] != tree[i - 1]) {
                seqStartIndex = i;
            }

            sum++;
            max = Math.max(sum, max);
        }

        return max;
    }
}

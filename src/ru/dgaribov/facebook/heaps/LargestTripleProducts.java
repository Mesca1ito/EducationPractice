package ru.dgaribov.facebook.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author David Garibov
 */
public class LargestTripleProducts {

    public static void main(String[] args) {
        LargestTripleProducts tripleProducts = new LargestTripleProducts();
        System.out.println(Arrays.toString(tripleProducts.findMaxProduct(new int[]{1, 2, 3, 4, 5})));
    }

    int findProductOfThree(PriorityQueue<Integer> pq) {
        if (pq.size() < 3) return -1;
        int result = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 3; i++) {
            int el = pq.poll();
            stack.push(el);
            result *= el;
        }
        while (!stack.isEmpty()) {
            pq.add(stack.pop());
        }
        return result;
    }

    int[] findMaxProduct(int[] arr) {
        // Write your code here

        // we're having a priority queue
        // adding elemnt one after another
        //



        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int[] result = new int[arr.length];
        for (int i = 0; i< arr.length; i++) {
            pq.add(arr[i]);
            result[i] = findProductOfThree(pq);
        }
        return result;
    }
}

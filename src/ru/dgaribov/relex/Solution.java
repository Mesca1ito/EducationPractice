package ru.dgaribov.relex;/*
In a grocery store every day many orders are made for hundreds of items for a single product. Products are usually ordered in batches, meaning that you need to order some multiples of products whenever you are making the order. For instance, a milk cartons are sold by pallets containing 12 cartons.

Due to delivery constraints some times the order must be split to separate orders. Moreover, the individual orders must adhere to a certain batch size if possible. I.e. orders' sizes should be multiples of some value if that is possible.

In this case it's more important to respect total order size over batch size. Therefore the remainder for some product after calculating other values may be not conforming to the batch size.

The task:

Create a method "splitOrder" which takes a size of an order (integer), batch size (integer) and amount of sub-orders to create (integer) as input and returns an array consisting of the new sizes. The new sizes are rounded up if they have decimals.

An example:

splitOrder(orderSize: 10, batchSize: 1, subOrders: 2) => [5, 5]
splitOrder(orderSize: 10, batchSize: 1, subOrders: 3) => [4, 4, 2]
splitOrder(orderSize: 10, batchSize: 3, subOrders: 3) => [6, 4, 0]

Let's illustrate the last case in detail:

1. Order of 10 is divided into 3 sub orders. The result would be [3.33, 3.33, 3.33] without rounding. However, as we round upwards, the first item will be 6 because the next multiplier divisible by three after 3.33 is 6.
2. Now we have 4 unfulfilled order items out of the original 10. The next pre-calculated order size would be again 3.33 and would rounded up 6 likewise. However, as we have only 4 unfulfilled order items left, we use that the final value as the total order size is to be prioritized over batch size.
3. Last, we would have a third order to make. However, as we now have already fulfilled the order for 10 order items, there is nothing to order and hence the last value will be 0.
*/

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;

public class Solution {



    @Test
    public void testEvenSplit() {
        Assert.assertArrayEquals(new int[] {5, 5}, splitOrder(10, 1, 2));
    }

    @Test
    public void testNeedsRounding() {
        Assert.assertArrayEquals(new int[] {4, 4, 2}, splitOrder(10, 1, 3));
    }

    @Test
    public void testNotEnoughForAll() {
        Assert.assertArrayEquals(new int[] {6, 4, 0}, splitOrder(10, 3, 3));
    }

    public static void main(String[] args) {
        JUnitCore.main("ru.dgaribov.relex.Solution");
    }

    public int[] splitOrder(final int orderSize, final int batchSize, final int subOrders) {

        int[] result = new int[subOrders];
        // return an array
        // orderSize / subOrders == 10.0 / 3 == 3.3333

        // This is how the order would evenly be split between the slots
        final double roundedSplit = Math.ceil(orderSize / (double)subOrders);
        // Adjust the split according to a multiplier of the batchSize
        final int subOrderSize = (int)Math.ceil(roundedSplit / batchSize) * batchSize;

        int remainingAmount = orderSize;

        for (int i = 0; i < subOrders; i++) {
            if (remainingAmount < subOrderSize) {
                result[i] = remainingAmount;
                remainingAmount = 0;
            } else {
                result[i] = subOrderSize;  // add either 6 or remainingAmount, depending on which is smaller
                remainingAmount -= subOrderSize;
            }
        }


        System.out.println("subOrderSize: " + subOrderSize);
        System.out.println(Arrays.toString(result));

        return result;
    }
}

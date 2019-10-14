package ru.dgaribov.dynamicprogrammingforinterviews;


import java.util.*;

public class Knapsack {

    public static int knapsack(Item[] items, int capacity) {

        int[] cache = new int[capacity + 1];
        for (Item i: items) {
            int[] newCache = new int[capacity + 1];
            for (int j = 0; j <= capacity; j++) {
                if (i.weight > j) {
                    newCache[j] = cache[j];
                } else {
                    newCache[j] = Math.max(cache[j], cache[j - 1] + i.value);
                }
            }
            cache = newCache;
        }

        return cache[capacity];
    }


    /**
     * Recursively computes biggest value for given list of items and knapsack capacity
     *
     * @param items list of items
     * @param W     remaining weight that a knapsack can take
     * @param i     index of an item
     * @return max value for a knapsack
     */
    private static int knapsackRec(Item[] items, int W, int i, Map<Integer, Map<Integer, Integer>> cache) {
        if (i == items.length) return 0;

        if (!cache.containsKey(i)) {
            cache.put(i, new HashMap<Integer, Integer>());
        }

        Integer cached = cache.get(i).get(W);
        if (cached != null) return cached;

        int toReturn;
        if (W - items[i].weight < 0) {
            toReturn = knapsackRec(items, W, i + 1, cache);
        } else {
            toReturn = Math.max(knapsackRec(items, W - items[i].weight, i + 1, cache) + items[i].value,
                    knapsackRec(items, W, i + 1, cache));
        }

        cache.get(i).put(W, toReturn);
        return toReturn;
    }


    private static int knapsackFirst(List<Item> items,
                                     int currentWeight,
                                     int currentValue,
                                     int capacity) {


        int maxValue = 0;

        if (items.size() == 1) {
            if (currentWeight + items.get(0).weight > capacity) return currentValue;
            return currentValue + items.get(0).value;
        }

        for (Item item : items) {
            List<Item> subList = new ArrayList<>(items.size() - 1);
            for (Item subItem : items) {
                if (subItem.equals(item)) continue;
                subList.add(subItem);
            }

            if (currentWeight + item.weight > capacity) {
                continue;
            }

            int subResult = knapsackFirst(subList, currentWeight + item.weight, currentValue + item.value, capacity);
            if (subResult > maxValue) maxValue = subResult;
        }

        return maxValue;
    }

    public static void main(String[] args) {


        Item[] items = new Item[]{
                new Item(2, 6),
                new Item(4, 5),
                new Item(3, 7),
        };

        int result = knapsack(items, 6); //should be 13
        System.out.println(result);
    }
}


class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return weight == item.weight &&
                value == item.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, value);
    }
}
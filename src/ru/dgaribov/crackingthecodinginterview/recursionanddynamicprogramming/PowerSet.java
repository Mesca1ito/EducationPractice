package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {


    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>();
        set.add(1);
        set.add(3);
        System.out.println(getSubsets(set, 0));
    }


    private static List<List<Integer>> getSubsets(List<Integer> set, int index) {
        List<List<Integer>> allSubsets;
        if (set.size() == index) { //Base case - add empty set
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            List<List<Integer>> moreSubsets = new ArrayList<>();
            for (List<Integer> subset: allSubsets) {
                List<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }
            allSubsets.addAll(moreSubsets);
        }
        return allSubsets;
    }



    private static List<List<Integer>> getSubsets2(List<Integer> set) {

        List<List<Integer>> allSubsets = new ArrayList<>();
        int max = 1 << set.size();
        for (int k = 0; k < max; k++) {
            List<Integer> subset = convertIntToSet(k, set);
            allSubsets.add(subset);
        }

        return allSubsets;
    }

    private static List<Integer> convertIntToSet(int x, List<Integer> set) {

        List<Integer> subset = new ArrayList<>();
        int index = 0;
        for (int k = x; k > 0; k >>= 1) {
            if ((k & 1) == 1) {
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }
}

package ru.dgaribov.amazon;

import java.util.List;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.stream.Collectors;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED

    /**
     *
     * @param deviceCapacity
     * @param foregroundAppList list of (appId, memory)
     * @param backgroundAppList list of (appId, memory)
     * @return list of two app id's (foreground and background) that utilises memory optimal (max mossible combination
     * of two foreground and background apps for given memory)
     */
    List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {
        // WRITE YOUR CODE HERE
        List<List<Integer>> foregroundFiltered =
                filterAndSortAppsByCapacity(deviceCapacity, foregroundAppList);

        List<List<Integer>> backgroundFiltered =
                filterAndSortAppsByCapacity(deviceCapacity, backgroundAppList);


        List<List<Integer>> result = new ArrayList<>(foregroundFiltered.size()
                + backgroundFiltered.size());
        Set<Integer> usedForeground = new HashSet<>();
        Set<Integer> usedBackground = new HashSet<>();

        List<Combination> combinations = getCombinationsList(foregroundFiltered,
                backgroundFiltered,
                deviceCapacity);

        for (Combination combination: combinations) {
            if (!usedForeground.contains(combination.foregroundAppId)
                    && !usedBackground.contains(combination.backgroundAppId)) {
                List<Integer> resultEl = new ArrayList(2);
                resultEl.add(combination.foregroundAppId);
                resultEl.add(combination.backgroundAppId);
                result.add(resultEl);
                usedForeground.add(combination.foregroundAppId);
                usedBackground.add(combination.backgroundAppId);
            }
        }

        return result;
    }
    // METHOD SIGNATURE ENDS

    private List<List<Integer>> filterAndSortAppsByCapacity(int deviceCapacity,
                                                            List<List<Integer>> appList) {
        return appList
                .stream()
                .filter(app -> app.get(1) <= deviceCapacity)
                .sorted((app1, app2) -> app2.get(1).compareTo(app1.get(1)))
                .collect(Collectors.toList());
    }

    private List<Combination> getCombinationsList(
            List<List<Integer>> foregroundFiltered,
            List<List<Integer>> backgroundFiltered,
            int deviceCapacity) {
        List<Combination> combinations = new ArrayList<>();

        for (List<Integer> foregroundApp: foregroundFiltered) {
            for (List<Integer> backgroundApp: backgroundFiltered) {
                int totalMemory = foregroundApp.get(1) + backgroundApp.get(1);
                if (totalMemory <= deviceCapacity ) {
                    combinations.add(
                            new Combination(
                                    foregroundApp.get(0),
                                    backgroundApp.get(0),
                                    totalMemory)
                    );
                }
            }
        }



        combinations = combinations
                .stream()
                .sorted((c1, c2) -> Integer.compare(c2.totalMemory, c1.totalMemory))
                .collect(Collectors.toList());

        return combinations;
    }

    class Combination {
        Integer foregroundAppId;
        Integer backgroundAppId;
        int totalMemory;

        public Combination(Integer foregroundAppId,
                           Integer backgroundAppId,
                           int totalMemory) {
            this.foregroundAppId = foregroundAppId;
            this.backgroundAppId = backgroundAppId;
            this.totalMemory = totalMemory;
        }
    }
}

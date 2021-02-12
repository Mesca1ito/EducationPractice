package ru.dgaribov.yandexcode.interview20210211;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author David Garibov
 */
class UserStatistics {
    int limit;
    int k;
    int robotCount = 0;
    Map<Long, Long> map = new HashMap<>();
    List<Pair<Long, Long>> events = new LinkedList<>();


    public UserStatistics(int k, int limit) {
        this.k = k;
        this.limit = limit;
    }

    public void event(long time, long userId) {
        // place your code here
        Pair<Long, Long> event = new Pair<>(time, userId);
        Long count = map.get(userId);
        count = count == null ? 0 : count;
        map.merge(userId, 1, Integer::sum);
        if (count  + 1 == limit) robotCount++;
        events.add(event);
    }

    public int robotCount(long time) {
        // place your code here
        for (Pair<Long, Long> event: events) {
            if (time - event.first() > this.limit) {
                events.delete(event);
                int count = map.get(event.second());
                if (count == limit) robotCount -= 1;
                map.set(event.second,  count - 1);
            } else {
                break;
            }
        }


        return robotCount;
    }
}

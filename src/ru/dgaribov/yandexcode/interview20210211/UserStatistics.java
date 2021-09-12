package ru.dgaribov.yandexcode.interview20210211;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You need to design an app that will store events for users and check stored events for robot activity.
 * You can consider the user as a robot if there is more than {LIMIT} event for a user per given amount of time.
 * {LIMIT} is an app setting and could be a constructor argument.
 *
 * Two methods to be implemented:
 * void event(long timestamp, long userId) - event storing method. We need those events only to test them against robot activity and no more further.
 * int robotsCount(long time) - how many robots we have seen for the past {time} milliseconds
 *
 * Consider that robotsCount will be called time after time on a regular basis.
 *
 * You need to design this app as efficiently as possible to prevent OutOfMemoryError.
 *
 * Use Java for coding.
 *
 * @author David Garibov
 */
class UserStatistics {
//    int limit;
//    int k;
//    int robotCount = 0;
//    Map<Long, Long> map = new HashMap<>();
//    List<Pair<Long, Long>> events = new LinkedList<>();
//
//
//    public UserStatistics(int k, int limit) {
//        this.k = k;
//        this.limit = limit;
//    }
//
//    public void event(long time, long userId) {
//        // place your code here
//        Pair<Long, Long> event = new Pair<>(time, userId);
//        Long count = map.get(userId);
//        count = count == null ? 0 : count;
//        map.merge(userId, 1, Integer::sum);
//        if (count  + 1 == limit) robotCount++;
//        events.add(event);
//    }
//
//    public int robotCount(long time) {
//        // place your code here
//        for (Pair<Long, Long> event: events) {
//            if (time - event.first() > this.limit) {
//                events.delete(event);
//                int count = map.get(event.second());
//                if (count == limit) robotCount -= 1;
//                map.set(event.second,  count - 1);
//            } else {
//                break;
//            }
//        }
//
//
//        return robotCount;
//    }
}

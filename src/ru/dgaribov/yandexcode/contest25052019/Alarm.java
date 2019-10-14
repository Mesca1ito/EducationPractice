package ru.dgaribov.yandexcode.contest25052019;

import java.util.*;

public class Alarm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] arguments = line.split(" ");
        int N = Integer.parseInt(arguments[0]);
        int X = Integer.parseInt(arguments[1]);
        int K = Integer.parseInt(arguments[2]);

        line = scanner.nextLine();
        int[] Ti = new int[N];
        arguments = line.split(" ");
        for (int i = 0; i < arguments.length; i++) {
            Ti[i] = Integer.parseInt(arguments[i]);
        }

        Alarm alarm = new Alarm();
        int wakeUpAlarm = alarm.findWakeUpTime(N, X, K, Ti);
        System.out.println(wakeUpAlarm);
    }


    private int findWakeUpTime(int N, int X, int K, int[] Ti) {

        Comparator<Integer> comparator = Integer::compareTo;


        PriorityQueue<Integer> alarmQueue = new PriorityQueue<>(comparator);
        Set<Integer> alarmSet = new HashSet<>();
        for (int t: Ti) {
            alarmSet.add(t);
        }
        alarmQueue.addAll(alarmSet);

        int alarm = Ti[0];
        for (int i = 0; i < K; i++) {
            alarm = alarmQueue.poll();
            int newAlarm = alarm + X;
            if (!alarmQueue.contains(newAlarm)) {
                alarmQueue.add(newAlarm);
            }
        }

        return alarm;
    }

   private void alternative() {

        // Можно сначала убрать лишние будильники проверив остаток от деления времени звонка на интервал (общий для всех будильников).
        // Для тех будильников, у которых он будет одинаковым - оставить только первый
        // Далее с помощью формулы max((T - Ti / X), 0) можно определить сколько раз будильник прозвенит к моменту времени T.
        // Теперь с помощью этой формулы и бинарного поиска мы найдем момент времени, когда количество звонов будет равно
       // заданному K

   }



}

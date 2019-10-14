package ru.dgaribov.yandexcode.contest25052019;

import java.util.*;

public class ChessContest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int k = Integer.parseInt(line);

        List<Pair> pairs = new ArrayList<>();


        for (int i = 0; i < Math.pow(2, k) - 1; i++) {
            line = scanner.nextLine();
            String[] names = line.split(" ");
            Pair pair = new Pair(names[0], names[1]);
            pairs.add(pair);
        }

        ChessContest chessContest = new ChessContest();
        String result = chessContest.getFinalists(pairs, k);
        System.out.println(result);

    }


    private String getFinalists(List<Pair> pairs, int k) {
        Map<String, Integer> namesToGamesMap = new HashMap<>();
        for (Pair pair : pairs) {
            namesToGamesMap.merge(pair.getFirst(), 1, (oldVal, newVal) -> oldVal + newVal);
            namesToGamesMap.merge(pair.getSecond(), 1, (oldVal, newVal) -> oldVal + newVal);
        }


        if (namesToGamesMap.keySet().size() != (int) Math.pow(2, k)) return "NO SOLUTION";

        List<String> finalists = new ArrayList<>(2);
        for (Map.Entry<String, Integer> entry : namesToGamesMap.entrySet()) {
            if (entry.getValue() == k) {
                finalists.add(entry.getKey());
            }
        }
        return String.join(" ", finalists);
    }
}


class Pair {
    private String first;
    private String second;

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }
}
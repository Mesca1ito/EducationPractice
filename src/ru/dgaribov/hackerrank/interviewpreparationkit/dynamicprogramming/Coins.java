package ru.dgaribov.hackerrank.interviewpreparationkit.dynamicprogramming;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Coins {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new  FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Long> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = Result.getWays(n, c);

        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }


    static class Result {

        /*
         * Complete the 'getWays' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. LONG_INTEGER_ARRAY c
         */

        public static long getWays(int n, List<Long> c) {
            return getWays((long) n, 0, c);
        }

        private static long getWays(long n, int coinIndex, List<Long> c) {
            if (coinIndex >= c.size() || n == 0) return 0;
            long remaining = n < c.get(coinIndex) ? 0 : n % c.get(coinIndex);
            return getWays(n, coinIndex + 1, c) + getWays(remaining, coinIndex + 1, c) + 1;
        }

    }
}

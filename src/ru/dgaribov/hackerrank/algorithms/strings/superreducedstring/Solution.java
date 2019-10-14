package ru.dgaribov.hackerrank.algorithms.strings.superreducedstring;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the superReducedString function below.
    private static String superReducedString(String s) {
        char[] originalChars = s.toCharArray();
        LinkedList<Character> resultCharsDeque = new LinkedList<>();
        for (Character theChar: originalChars) {
            if (theChar.equals(resultCharsDeque.peekLast())) {
                resultCharsDeque.pollLast();
                continue;
            }
            resultCharsDeque.addLast(theChar);
        }

        if (resultCharsDeque.isEmpty()) {
            return "Empty String";
        }

        char[] resultChars = new char[resultCharsDeque.size()];
        for (int i = 0; i < resultCharsDeque.size(); i++) {
            char theChar = resultCharsDeque.get(i);
            resultChars[i] = theChar;
        }

        return String.valueOf(resultChars);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
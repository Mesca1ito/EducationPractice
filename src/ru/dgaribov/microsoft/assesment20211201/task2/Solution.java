package ru.dgaribov.microsoft.assesment20211201.task2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author David Garibov
 */
public class Solution {
    /**
     * For this solution we use set
     * If we see a repeating character - that means that we need to split the word
     *
     * @param S input sting
     *
     * @return number of splits
     */
    public int solution(String S) {
        // write your code in Java SE 8
        Set<Character> charSet = new HashSet<>();
        int numberOfWords = 1;
        for (char ch : S.toCharArray()) {
            if (!charSet.contains(ch)) {
                charSet.add(ch);
            } else {
                numberOfWords++;
                charSet.clear();
                charSet.add(ch);
            }
        }

        return numberOfWords;
    }
}

package ru.dgaribov.leetcode.facebook.recursion;

import java.util.*;

/**
 * @author David Garibov
 */
public class LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber app = new LetterCombinationsOfPhoneNumber();
        List<String> result = app.letterCombinations("234");
        System.out.println(result);
    }


    Map<String, List<String>> map = new HashMap<>();

    public LetterCombinationsOfPhoneNumber() {
        map.put("2", Arrays.asList("a", "b", "c"));
        map.put("3", Arrays.asList("d", "e", "f"));
        map.put("4", Arrays.asList("g", "h", "i"));
        map.put("5", Arrays.asList("j", "k", "l"));
        map.put("6", Arrays.asList("m", "n", "o"));
        map.put("7", Arrays.asList("p", "q", "r", "s"));
        map.put("8", Arrays.asList("t", "u", "v"));
        map.put("9", Arrays.asList("w", "x", "y", "z"));
    }


    public List<String> letterCombinations(String digits) {
        List<String> words = new ArrayList<>();
        if (digits.length() == 0) return words;
        if (digits.length() == 1) return map.get(digits);

        String remaining = digits.substring(1);
        String digit = "" + digits.charAt(0);
        List<String> letters = map.get(digit);
        List<String> otherCombinations = letterCombinations(remaining);
        for (String letter : letters) {
            for (String otherCombination : otherCombinations) {
                String newCombination = letter + otherCombination;
                words.add(newCombination);
            }
        }
        return words;
    }
}

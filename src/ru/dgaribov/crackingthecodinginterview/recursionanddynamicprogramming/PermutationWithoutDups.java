package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class PermutationWithoutDups {
    public List<String> getPerms(String str) {
        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = getPerms(remainder);
        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                String s = insertCharAt(str, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }


    private String insertCharAt(String str, char ch, int i) {
        String before = str.substring(0, i);
        String after = str.substring(i);
        return before + ch + after;
    }

    public List<String> getPerms2(String remainder) {
        int len = remainder.length();
        List<String> result = new ArrayList<>();

        if (len == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1);
            List<String> partials = getPerms2(before + after);

            for (String s : partials) {
                result.add(remainder.charAt(i) + s);
            }
        }

        return result;
    }


    public List<String> getPerms3(String str) {
        List<String> result = new ArrayList<>();
        getPerms3Rec("", str, result);
        return result;
    }

    private void getPerms3Rec(String prefix, String remainder, List<String> result) {
        if (remainder.length() == 0) result.add(prefix);

        int len = remainder.length();
        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1);
            getPerms3Rec(prefix + remainder.charAt(i), before + after, result);
        }
    }

    public static void main(String[] args) {
        PermutationWithoutDups app = new PermutationWithoutDups();
        List<String> result = app.getPerms3("aaa");
        System.out.println(result);
    }
}

package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class Parens {
    void addParen(List<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) return;

        if (leftRem == 0 && rightRem == 0) {
            list.add(new String(str));
            return;
        }

        str[index] = '(';
        addParen(list, leftRem - 1, rightRem, str, index + 1);

        str[index] = ')';
        addParen(list, leftRem, rightRem - 1, str, index + 1);
    }

    List<String> parens(int count) {
        char[] str = new char[count * 2];
        List<String> result = new ArrayList<>();
        addParen(result, count, count, str, 0);
        return result;
    }

    public static void main(String[] args) {
        Parens parens = new Parens();
        System.out.println(parens.parens(1));
    }
}

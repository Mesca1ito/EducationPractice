package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class URLify {
    public static String replaceSpaces(char[] str, int trueLength) {
        int spacesNumber = 0;
        for (int i = 0; i < trueLength;i++) {
            if (str[i] == ' ') spacesNumber++;
        }

        int index = trueLength + 2 * spacesNumber;
        if (trueLength < str.length) str[trueLength] = '\0';
        for (int i = trueLength - 1; i >= 0; i--) {
            if (str[i] == '\u0000' || str[i] == ' ') {
                str[index - 3] = '%';
                str[index - 2] = '2';
                str[index - 1] = '0';
                index -= 3;
            } else {
                str[--index] = str[i];
            }
        }
        return new String(str);
    }
    public static void main(String[] args) {
        String s = "a b c                   ";
        System.out.println(replaceSpaces(s.toCharArray(), 5));
    }
}

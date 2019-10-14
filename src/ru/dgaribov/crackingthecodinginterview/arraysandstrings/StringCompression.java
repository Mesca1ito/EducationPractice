package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class StringCompression {
    public static String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }

        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    public static String compress2(String str) {
        int compressedLength = countCompression(str);
        if (compressedLength >= str.length()) return str;
        return compress(str);
    }

    private static int countCompression(String str) {
        if (str == null) return 0;
        int count = 0;
        int compressed = 0;
        for (int i = 0; i < str.length(); i++) {
            compressed++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                count += 1 + String.valueOf(compressed).length();
                compressed = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(compress2("aaavvccccd"));
        System.out.println(compress2("abcd"));
    }
}

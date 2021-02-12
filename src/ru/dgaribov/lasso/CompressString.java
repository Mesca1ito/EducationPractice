package ru.dgaribov.lasso;

/**
 * QUESTION:
 * Given a string s, for example aaabbc. White a compression function so the result would be  a3b2c
 *
 * @author David Garibov
 */
public class CompressString {

    public static void main(String[] args) {
        CompressString app = new CompressString();
        System.out.println(app.compress("aaabbc"));
    }

    public String compress(String s) {
        if (s == null || s.length() < 2) return s;
        StringBuilder compressed = new StringBuilder();
        int counter = 1;
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);
            char previous = s.charAt(i - 1);
            if (current == previous) {
                counter++;
            } else {
                compressed.append(previous);
                if (counter > 1) compressed.append(counter);
                counter = 1;
            }
        }

        compressed.append(s.charAt(s.length() - 1));
        if (counter > 1) {
            compressed.append(counter);
        }

        return compressed.toString();
    }
}

package ru.dgaribov.facebook;

/**
 * @author David Garibov
 */
public class RotationCipher {
    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        char cha = 'a';
        char chz = 'z';
        char chA = 'A';
        char chZ = 'Z';
        char ch0 = '0';
        char ch9 = '9';

        char[] ar = input.toCharArray();
        char[] newAr = new char[ar.length];

        for (int i = 0; i < ar.length; i++) {
            char ch = ar[i];
            char left = Character.MIN_VALUE;
            char right = Character.MIN_VALUE;
            if (cha <= ch && chz >= ch) {
                left = cha;
                right = chz;
            }
            if (chA <= ch && chZ >= ch) {
                left = chA;
                right = chZ;
            }
            if (ch0 <= ch && ch9 >= ch) {
                left = ch0;
                right = ch9;
            }

            if (left != Character.MIN_VALUE) {
                ch += rotationFactor % (right - left + 1);
                if (ch > right) {
                    ch = (char) (left + (ch - right) - 1);
                }
            }

            newAr[i] = ch;
        }

        return String.valueOf(newAr);
    }

    public static void main(String[] args) {
        RotationCipher ro = new RotationCipher();
        String result = ro.rotationalCipher("Zebra-493", 3);
        System.out.println(result);
    }
}

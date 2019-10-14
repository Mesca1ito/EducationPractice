package ru.dgaribov.crackingthecodinginterview.arraysandstrings;

public class PermutationOfPalindrome {
    public static boolean isPermutationOfPalindrome(String str) {
        int[] freqTable = buildCharFrequencyTable(str);
        boolean oddChar = false;
        for (int freq : freqTable) {
            if (freq % 2 != 0) {
                if (oddChar) return false;
                oddChar = true;
            }
        }
        return true;
    }

    public static boolean isPermutationOfPalindrome2(String str) {
        int[] freqTable = new int[charIndex('z') + 1];
        int oddNumber = 0;
        for (char ch : str.toCharArray()) {
            freqTable[charIndex(ch)]++;
            if (freqTable[charIndex(ch)] % 2 == 0) oddNumber = Math.max(0, oddNumber - 1);
            else {
                oddNumber++;
            }
        }
        return oddNumber <= 1;
    }

    public static boolean isPermutationOfPalindrome3(String str) {
        int bitVerctor = createBitVerctor(str);
        return bitVerctor == 0 || checkExactlyOneBitSet(bitVerctor);
    }

    private static int createBitVerctor(String str) {
        int bitVector = 0;
        for (char ch: str.toCharArray()) {
            int x = charIndex(ch);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    /**
     * Toggle the ith bit of the integer
     * @param bitVector
     * @param index
     * @return
     */
    private static int toggle(int bitVector, int index) {
        if (index < 0) return bitVector;

        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    private static int[] buildCharFrequencyTable(String str) {
        int[] table = new int[charIndex('z') + 1];
        for (char ch : str.toCharArray()) {
            table[charIndex(ch)]++;
        }
        return table;
    }

    private static int charIndex(char ch) {
        if ((int) 'a' <= (int) ch && (int) ch <= (int) 'z') {
            return (int) ch - (int) 'a';
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrome2("aaccdbb"));
    }
}

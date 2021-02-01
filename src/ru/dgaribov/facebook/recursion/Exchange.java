package ru.dgaribov.facebook.recursion;

/**
 * @author David Garibov
 */
public class Exchange {
    int[] extractFromAr(int[] ar, int i) {
        int[] subAr = new int[ar.length - 1];
        boolean inserted = false;
        for (int k = 0; k < ar.length; k++) {
            if (k != i) {
                if (inserted) {
                    subAr[k - 1] = ar[k];
                } else {
                    subAr[k] = ar[k];
                }
            } else {
                inserted = true;
            }
        }
        return subAr;
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        // Write your code here
        if (denominations.length == 1) return targetMoney % denominations[0] == 0;
        for (int i = 0; i < denominations.length; i++) {
            if (canGetExactChange(targetMoney % denominations[i], extractFromAr(denominations, i))) return true;
        }
        return false;
    }
}

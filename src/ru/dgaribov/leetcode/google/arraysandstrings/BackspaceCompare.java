package ru.dgaribov.leetcode.google.arraysandstrings;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author David Garibov
 */
public class BackspaceCompare {
    public boolean backspaceCompare(String S, String T) {
        int i = 0;
        int j = 0;

        LinkedList<Character> sList = new LinkedList<>();
        LinkedList<Character> tList = new LinkedList<>();

        while (i < S.length() || j < T.length()) {
            if (i < S.length()) {
                char ch = S.charAt(i);
                if (ch == '#') {
                    if (!sList.isEmpty()) {
                        sList.removeLast();
                    }
                } else {
                    sList.add(ch);
                }
                i++;
            }

            if (j < T.length()) {
                char ch = T.charAt(j);
                if (ch == '#') {
                    if (!tList.isEmpty()) {
                        tList.removeLast();
                    }
                } else tList.add(ch);
                j++;
            }
        }

        if (sList.size() != tList.size()) {
            return false;
        }

        Iterator<Character> tListIterator = tList.iterator();
        for (char sChar : sList) {
            char tChar = tListIterator.next();
            if (sChar != tChar) return false;
        }

        return true;
    }


    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;

    }
}

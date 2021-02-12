package ru.dgaribov.yandexcode.interview20210209;

import java.util.Iterator;

/**
 * @author David Garibov
 */
public class MyIterator implements Iterator<Character> {

    public static void main(String[] args) {
        MyIterator myIterator = new MyIterator("abc");
        while (myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }
    }

    private final String str;
    private int pointer = 0;

    public MyIterator(String str) {
        this.str = str;
    }

    @Override
    public boolean hasNext() {
        return str != null && !str.isEmpty() && pointer < str.length();
    }

    @Override
    public Character next() {
        return str.charAt(pointer++);
    }
}

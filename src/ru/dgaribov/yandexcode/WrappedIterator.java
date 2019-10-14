package ru.dgaribov.yandexcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class WrappedIterator implements Iterator<String> {

    public static Iterator<String> unwrap(Iterator<?> it) {
        return new WrappedIterator(it);
    }

    private Iterator<?> it;

    private Deque<Iterator> iteratorStack = new ArrayDeque<>();
    private String nextStr;

    public WrappedIterator(Iterator<?> it) {
        this.it = it;
    }

    public boolean hasNext() {
        if (nextStr != null) return true;
        Iterator it = iteratorStack.peek();
        if (it == null) return false;
        Object nextEl;
        try {
            nextEl = it.next();
        } catch(NoSuchElementException ex) {
            if (iteratorStack.isEmpty()) return false;
            iteratorStack.pop();
            return hasNext();
        }
        if (nextEl instanceof Iterator) {
            iteratorStack.push((Iterator)it.next());
            return hasNext();
        }
        nextStr = (String) nextEl;
        return true;
    }

    public String next() {
        if (hasNext()) {
            String result = nextStr;
            this.nextStr = null;
            return result;
        }

        throw new NoSuchElementException();
    }
}

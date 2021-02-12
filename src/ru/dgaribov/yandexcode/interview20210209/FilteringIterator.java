package ru.dgaribov.yandexcode.interview20210209;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author David Garibov
 */
class FilteringIterator<T> implements Iterator<T> {
    private Predicate<T> predicate;
    private Iterator<T> source;

    private T curVal;

    private boolean nextCalled = true;


    public FilteringIterator(Iterator<T> source, Predicate<T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (!nextCalled) return true;
        while (source.hasNext()) {
            if (predicate.test(curVal = source.next())) {
                this.nextCalled = false;
                return true;
            }
        }
        this.nextCalled = true;
        return false;
    }

    //returns elements from source where #predicate is true
    @Override
    public T next() {
        if (!this.nextCalled) {
            this.nextCalled = true;
            return curVal;
        }
        this.nextCalled = true;
        if (hasNext()) return curVal;
        else {throw new NoSuchElementException();}
    }
}

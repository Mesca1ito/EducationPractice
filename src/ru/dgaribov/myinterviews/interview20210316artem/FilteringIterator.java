package ru.dgaribov.myinterviews.interview20210316artem;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author David Garibov
 */
public class FilteringIterator<T> implements Iterator<T> {

    private final Iterator<T> it;
    private final Predicate<T> predicate;
    private T foundElement;
    private boolean wasFoundInHasNextFlag;

    public FilteringIterator(Iterator<T> it, Predicate<T> predicate) {
        this.it = it;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (this.it.hasNext()) {
            if (this.wasFoundInHasNextFlag) {
                return true;
            }

            this.foundElement = this.it.next();
            if (predicate.test(this.foundElement)) {
                this.wasFoundInHasNextFlag = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (this.wasFoundInHasNextFlag) {
            this.wasFoundInHasNextFlag = false;
            return this.foundElement;
        }
        return null;
    }
}

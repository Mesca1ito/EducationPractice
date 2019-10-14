package ru.dgaribov.crackingthecodinginterview.stacksandqueues.threeinone;

public class MultiStack {
    private int[] values;
    private StackInfo[] info;

    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        /**
         * Check if an index on the full array is within the stack boundaries.
         * The stack can wrap around to the start of the array.
         *
         * @param index
         * @return
         */
        public boolean isWithinStackCapacity(int index) {
            if (index < 0 || index >= values.length) return false;

            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    public MultiStack(int numberOfStacks, int defaultSize) {
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(i * defaultSize, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }


    public void push(int stackNum, int value) throws StackIsFullException {
        if (allStackAreFull()) {
            throw new StackIsFullException();
        }

        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        /* Find the index of the top element in the array + 1, and increment the stack pointer. */
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }


    public int pop(int stackNum) throws StackIsEmptyException {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) throw new StackIsEmptyException();
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;
        stack.size--;
        return value;
    }

    public int peek(int stackNum) throws StackIsEmptyException {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) throw new StackIsEmptyException();
        return values[stack.lastElementIndex()];
    }


    /**
     * Shift items in stack over by one element. If we have available capacity,
     * then we'll end up shrinking the stack by one element.
     * If we don't have available capacity, then we'll need to shift the stack over too.
     *
     * @param stackNum
     */
    private void shift(int stackNum) {
        System.out.println("/// Shifting " + stackNum);

        StackInfo stack = info[stackNum];

        /*
         * If the stack is at its full capacity, then you need to move the next stack over by one element.
         * This stack can now claim the freed index.
         * */
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++;
        }

        /*
        Shift all elements in stack over by one.
         */
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        /* Adjust stack data. */
        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        info[stackNum].capacity++;

    }

    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    /*
    Adjust index to be within the range of 0 -> length - 1
     */
    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }


    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    private boolean allStackAreFull() {
        return numberOfElements() == values.length;
    }


    private int numberOfElements() {
        int size = 0;
        for (StackInfo stackInfo : info) {
            size += stackInfo.size;
        }

        return size;
    }
}

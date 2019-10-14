package ru.dgaribov.crackingthecodinginterview.stacksandqueues.threeinone;

public class FixedMultiStack {
    private final static int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[numberOfStacks * stackCapacity];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNum, int value) throws StackIsFullException {
        if (isFull(stackNum)) throw new StackIsFullException();
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) throws StackIsEmptyException{
        if (isEmpty(stackNum)) throw new StackIsEmptyException();
        sizes[stackNum]--;
        int value = values[indexOfTop(stackNum)];
        values[indexOfTop(stackNum)] = 0;
        return value;
    }

    public int peek(int stackNum) throws StackIsEmptyException {
        if (isEmpty(stackNum)) throw new StackIsEmptyException();
        return values[indexOfTop(stackNum)];
    }

    private boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    private boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }
}


class StackIsFullException extends Exception {

}

class StackIsEmptyException extends Exception {

}
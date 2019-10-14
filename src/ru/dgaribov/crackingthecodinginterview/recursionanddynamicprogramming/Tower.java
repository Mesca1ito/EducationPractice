package ru.dgaribov.crackingthecodinginterview.recursionanddynamicprogramming;

import java.util.Stack;

public class Tower {
    private Stack<Integer> disks;
    private int index;

    public Tower(int i) {
        disks = new Stack<>();
        index = i;
    }

    public int index() {
        return index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
            return;
        }
        disks.push(d);
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        System.out.println("Moving " + top + " from " + this.index() + " to " + t.index());
        t.add(top);
    }

    public void moveDisks(int n, Tower destination, Tower buffer) {
        if (n > 0) {
            moveDisks(n - 1, buffer, destination);
            moveTopTo(destination);
            buffer.moveDisks(n - 1, destination, this);
        }
    }


    public static void main(String[] args) {
        Tower tower = new Tower(1);
        tower.add(7);
        tower.add(6);
        tower.add(5);
        tower.add(4);
        tower.add(3);
        tower.add(2);
        tower.add(1);
        Tower tower2 = new Tower(2);
        Tower tower3 = new Tower(3);
        tower.moveDisks(3, tower3, tower2);
    }
}

package ru.dgaribov.amazon.assessment20191027;

import javax.xml.soap.Node;
import java.util.*;

public class Solution {
    //23280666058772
    public static void main(String[] args) {
        List<MyVector> allVectors = new ArrayList<MyVector>();
        allVectors.sort(Comparator.comparingInt(o -> o.start));
        Queue<Node> queue = new LinkedList<>();
//        Math.min()
    }
}

class MyVector {
    int start;
}

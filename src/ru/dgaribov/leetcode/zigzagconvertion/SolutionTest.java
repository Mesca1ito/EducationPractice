package ru.dgaribov.leetcode.zigzagconvertion;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void test1() {
        assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
    }

    @Test
    public void test2() {
        assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void test3() {
        assertEquals("AB", solution.convert("AB", 1));
    }

    @Test
    public void test4() {
        assertEquals("ABC", solution.convert("ABC", 1));
    }
}

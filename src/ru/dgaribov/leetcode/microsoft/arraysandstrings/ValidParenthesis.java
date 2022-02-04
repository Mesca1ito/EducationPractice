package ru.dgaribov.leetcode.microsoft.arraysandstrings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author David Garibov
 */
public class ValidParenthesis {

    public static void main(String[] args) {
        ValidParenthesis vp = new ValidParenthesis();
        boolean result = vp.isValid("()");
        System.out.println(result);
    }


    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch: s.toCharArray()) {
            if (!map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                char compliment = stack.pop();
                if (compliment != map.get(ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}

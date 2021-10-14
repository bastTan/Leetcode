package Others;

import java.util.HashMap;
import java.util.Stack;

public class LC20_ValidParentheses {
    private HashMap<Character, Character> map;

    public LC20_ValidParentheses() {
        map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
    }

    public boolean isValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        Stack<Character> stack = new Stack();
        for (char ch : s.toCharArray()) {
            if (isLeft(ch)) {
                stack.push(ch);
            } else if (isRight(ch)) {
                if (stack.isEmpty()) return false;
                char top = stack.peek();
                if (!isMatch(top, ch)) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char ch) {
        return map.containsKey(ch);
    }

    private boolean isRight(char ch) {
        for (char right : map.values()) {
            if (ch == right) return true;
        }
        return false;
    }

    private boolean isMatch(char l, char r) {
        return map.get(l) == r;
    }
}
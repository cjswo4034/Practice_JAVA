package algorithm.prev.programmers.Level2;

import java.util.Stack;

public class _12973 {
    public static void main(String[] args) {
        System.out.println(solution(""));
    }

    public static int solution(String input){
        Stack<Character> stack = new Stack<>();
        int index = 0; char ch;

        while (index < input.length()) {
            ch = input.charAt(index++);
            if (stack.isEmpty() || stack.peek() != ch) stack.push(ch);
            else stack.pop();
        }

        return stack.isEmpty() ? 1 : 0;
    }
}

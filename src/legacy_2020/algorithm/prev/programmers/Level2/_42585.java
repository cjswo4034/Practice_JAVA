package algorithm.prev.programmers.Level2;

import java.util.Stack;

public class _42585 {
    public static void main(String[] args){
        System.out.println("#" + 1 + " : " + solution("()(((()())(())()))(())"));
        System.out.println("#" + 1 + " : " + solution("((((()())(())()))(()))"));
    }

    public static int solution(String input) {
        Stack<Character> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '('){
                stack.push('(');
            } else {
                if (input.charAt(i - 1) == '('){
                    stack.pop();
                    result += stack.size();
                } else {
                    stack.pop();
                    result++;
                }
            }
            System.out.println(stack.size() + " : " + result);
        }

        return result;
    }
}
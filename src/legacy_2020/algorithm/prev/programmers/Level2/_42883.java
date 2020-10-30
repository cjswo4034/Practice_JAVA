package algorithm.prev.programmers.Level2;

import java.io.*;
import java.util.Stack;

public class _42883 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            String input = br.readLine();
            Integer.parseInt(br.readLine());
            System.out.println("#" + i + " : " + solution(input));
        }
    }
    public static String solution(String input) {
        StringBuilder sb = new StringBuilder(input);

        return sb.toString();
    }

    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}
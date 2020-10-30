package algorithm.prev.swExpert.Level4.Basic;

import java.io.*;
import java.util.Stack;

public class 계산기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] input, after;
        int T = 10;
        for(int t = 1 ; t <= T ; t++){
            Integer.parseInt(br.readLine());
            input = br.readLine().toCharArray();
            after = postOrder(input).toCharArray();

            sb.append("#").append(t).append(" ").append(calculate(after)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static int calculate(char[] input){
        Stack<Integer> stack = new Stack<>();
        int num1, num2;
        for (char curr : input){
            if (Character.isDigit(curr)){
                stack.add(curr - '0');
            } else {
                num1 = stack.pop();
                num2 = stack.pop();
                if (curr == '+') stack.add(num1 + num2);
                else stack.add(num1 * num2);
            }
        }

        return stack.pop();
    }

    private static String postOrder(char[] input){
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char curr : input) {
            if (Character.isDigit(curr)) sb.append(curr);
            else if (!stack.isEmpty()){
                if (curr == '+'){
                    while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                    stack.add(curr);
                } else if (curr == '*'){
                    while (!stack.isEmpty() && (stack.peek() != '(' && stack.peek() != '+')) sb.append(stack.pop());
                    stack.add(curr);
                } else if (curr == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
                    stack.pop();
                } else stack.add('(');
            } else stack.add(curr);
        }

        while(!stack.isEmpty()) sb.append(stack.pop());

        return sb.toString();
    }
}
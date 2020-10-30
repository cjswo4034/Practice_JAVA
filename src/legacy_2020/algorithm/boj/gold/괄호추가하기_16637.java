package algorithm.boj.gold;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 괄호추가하기_16637 {
	static int result = Integer.MIN_VALUE;
	static int[] num;
	static char[] sign;
	static boolean[] paren;
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); sc.nextLine();
        char[] input = sc.nextLine().toCharArray();
        num = new int[n / 2 + 1];
        sign = new char[n / 2];
        paren = new boolean[n / 2];
        
        int nIdx = 0, sIdx = 0;
        for (char ch: input) {
        	if (Character.isDigit(ch)) num[nIdx++] = ch - '0';
        	else sign[sIdx++] = ch;
        }
        
        solution2(0, num[0]);
        System.out.println(result);
	}
	
	static void solution(int depth) {
		if (depth >= paren.length) {
			result = Math.max(result, function());
			return;
		}
		
		solution(depth + 1);
		paren[depth] = true;
		solution(depth + 2);
		paren[depth] = false;
	}
	
	static void solution2(int depth, int value) {
		if (depth >= sign.length) {
			result = Math.max(result, value);
			return;
		}
		
		int nv = calculate(value, num[depth + 1], sign[depth]);
		solution2(depth + 1, nv);
		
		if (depth + 1 < sign.length) {
			nv = calculate(num[depth + 1], num[depth + 2], sign[depth + 1]);
			solution2(depth + 2, calculate(value, nv, sign[depth]));
		}
	}
	
	static int function() {
		int res = 0, len = paren.length;
		
		Deque<Integer> n = new LinkedList<>();
		Queue<Character> s = new LinkedList<>();
		n.add(num[0]);
		for (int i = 0, value; i < len; i++) {
			if (!paren[i]) value = num[i + 1];
			else value = calculate(n.pollLast(), num[i + 1], sign[i]);
			
			n.add(value);
			if (!paren[i]) s.add(sign[i]);
		}
		
		res = n.pollFirst();
		while (!s.isEmpty()) res = calculate(res, n.poll(), s.poll()); 
		return res;
	}
	
	static int calculate(int num1, int num2, char sign) {
		int res = num1;
		switch(sign) {
		case '+': res += num2; break;
		case '*': res *= num2; break;
		default: res -= num2; break;
		}
		return res;
	}
}

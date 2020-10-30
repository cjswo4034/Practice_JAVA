package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 맞춰봐_1248 {
	static int n;
	static int [] result;
	static char [][] input;
	
	public static void main(String[] args) throws Exception {
		// 1) 각 원소의 부호를 결정. (입력된 기호들의 1, 1 + n, 1 + n + (n - 1)...)
		// 2) 백트래킹으로 중복 순열을 구하여 입력값과 비교
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = new char[n][];
		result = new int[n];
		
		String tmp = br.readLine();
		for (int i = 0, pos = 0; i < n; i++) {
			input[i] = new char[n];
			for (int j = i; j < n; j++) {
				input[i][j] = tmp.charAt(pos++);
			}
		}
		
		solution(0);
		
		for (int res : result) System.out.print(res + " ");
	}
	
	static boolean solution(int depth) {
		if (depth == n) return true;
		
		for (int i = -10; i < 10; i++) {
			result[depth] = i;
			if (check(depth) && solution(depth + 1)) return true;
		}
		
//		if (input[depth][depth] == '0') {
//			result[depth] = 0;
//			if (check(depth) && solution(depth + 1)) return true;
//		} else {
//			int sign = input[depth][depth] == '+' ? 1 : -1;
//			for (int i = 1; i <= 10; i++) {
//				result[depth] = i * sign;
//			    if (check(depth) && solution(depth + 1)) return true;
//			}
//		}
		return false;
	}
	
	static boolean check(int depth) {
		int sum = 0;
		for (int i = depth; i >= 0; i--) {
			sum += result[i];
			if (input[i][depth] == '+' && sum <= 0) return false;
			if (input[i][depth] == '-' && sum >= 0) return false;
			if (input[i][depth] == '0' && sum != 0) return false;
		}
		return true;
	}
}

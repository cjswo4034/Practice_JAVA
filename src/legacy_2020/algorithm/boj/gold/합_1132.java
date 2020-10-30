package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 합_1132 {
	static int n;
	static int[] numCache = new int[10];
	static long result;
	static char[][] inputs;
	static boolean[] used = new boolean[10];
	static boolean[] availableChar = new boolean[10];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		inputs = new char[n][];
		for (int i = 0; i < n; i++) {
			inputs[i] = br.readLine().toCharArray();
			for (char ch: inputs[i]) availableChar[ch - 'A'] = true;
		}
		
		dfs('A');
		System.out.println(result);
		
		// 1) A ~ J 까지 0 ~ 9의 숫자를 중복되지 않게 부여
		//    - 주어진 문자에 숫자가 전부 부여되면 숫자로 치환하여 합을 구함.
		// 1. 주어진 문자를 저장한다.
		// 2. 차례대로 순회하여 숫자를 순차적으로 중복되지 않게 부여한다.
		// 3. 주어진 모든 문자에 숫자가 부여되면 숫자로 치환한다.
		// 4. 치환된 두 개의 숫자를 더하여 합을 구한다.
		
		// 2) 각 문자가 위치한 자릿수를 파악
		// 1. 주어진 문자열들을 순회하여 문자의 위치를 기록
		// 2. 높은 자리에 많이 기록된 문자가 앞에 오도록 정렬
		// 3. 앞에서부터 9 -> 0으로 문자에 숫자 부여
	}

	static void dfs(int ch) {
		if (ch == 'J' + 1) {
			result = Math.max(result, calculate());
			return;
		}
		
		// 주어진 문자인가?
		// 아니라면 다음 문자로 넘어감
		if (!availableChar[ch - 'A']) {
			dfs(ch + 1);
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			// 사용된 숫자인가?
			if (used[i]) continue;
			
			used[i] = true;
			numCache[ch - 'A'] = i;
			dfs(ch + 1);
			used[i] = false;
		}
	}
	

	static long calculate() {
		long res = 0, tmp;
		for (char[] chs: inputs) {
			tmp = charToNum(chs);
			if (tmp == -1) return -1;
			res += tmp;
		}
		
		return res;
	}
	
	static long charToNum(char[] arr) {
		if (numCache[arr[0] - 'A'] == 0) return -1;
		
		long res = 0;
		for (char ch: arr) {
			res *= 10;
			res += numCache[ch - 'A'];
		}
		return res;
	}
}

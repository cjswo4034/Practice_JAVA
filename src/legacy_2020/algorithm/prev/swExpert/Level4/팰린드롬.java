package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 팰린드롬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		
		char[] input;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, ans = 0; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			input = br.readLine().toCharArray();
			
			THIS: for (int i = input.length; i >= 0; i--) {
				for (int j = 0; j <= input.length - i; j++) {
					if (isPalin(input, j, i)) {
						ans = Math.max(ans, i);
						break THIS;
					}
				}
			}
			
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static boolean isPalin(char[] str, int start, int len) {
		for (int i = 0; i < len / 2; i++) {
			if (str[start + i] != str[start + len - i - 1])
				return false;
		}
		return true;
	}
}

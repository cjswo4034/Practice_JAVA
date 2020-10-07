package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _6718 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			int num = Integer.parseInt(br.readLine());
			
			int result = cnt(num) - 2;
			result = result > 5 ? 5 : result;
			sb.append(result >= 0 ? result : 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int cnt(int num) {
		int cnt = 0;
		while(num > 0) {
			num /= 10;
			cnt++;
		}
		
		return cnt;
	}
}

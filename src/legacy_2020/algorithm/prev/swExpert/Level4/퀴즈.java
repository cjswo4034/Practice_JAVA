package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 퀴즈 {
	private final static int INF = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		long[] arr = initArr();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			int N = Integer.parseInt(br.readLine());
			
			sb.append(arr[N]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static long[] initArr() {
		long[] arr = new long[1000001];
		for (int i = 1; i < 1000001; i++) 
			arr[i] = (arr[i - 1] + solve(i, i)) % INF;
		return arr;
	}
	
	private static long solve(long base, int exp) {
		if (base == 1) return 1;
		long ans = 1;
		while (exp > 0) {
			if ((exp & 1) > 0) ans = (ans * base) % INF;
			base = (base * base) % INF;
			exp >>= 1;
		}
		return ans;
	}
}

package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5642 {
	static int n, result;
	static int[] array, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			n = Integer.parseInt(br.readLine());
			dp = new int[n];
			array = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			result = dp[0] = array[0];
			for (int i = 1; i < n; i++) {
				dp[i] = Math.max(array[i], dp[i - 1] + array[i]);
				result = Math.max(result, dp[i]);
			}
			
			sb.append(result);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

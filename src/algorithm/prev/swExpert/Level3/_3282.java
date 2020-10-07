package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3282 {
	static int n, k, result;
	static int[] v, c;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			v = new int[n + 1];	// 부피
			c = new int[n + 1];	// 가치
			dp = new int[k + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				c[i] = Integer.parseInt(st.nextToken());
			}
						
			for (int i = 1; i <= k; i++) {
				for (int j = 1; j <= n; j++) {
					if(i >= v[j]) dp[i][j] = Math.max(dp[i][j - 1], dp[i - v[j]][j - 1] + c[j]);
					else dp[i][j] = dp[i][j - 1];
				}
			}
			
			sb.append(dp[k][n]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

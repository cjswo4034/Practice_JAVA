package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 앱_7579 {
	static int n, m, result = 987654321;
	static int[] a, c;
	static int[][] dp; // dp[cost][idx] : cost가 주어졌을 때 idx를 사용했을 경우 최대 비용?

	public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        a = new int[n + 1];
        c = new int[n + 1];
        dp = new int[10001][n + 1];
        
        initArr(a, new StringTokenizer(br.readLine()));
        initArr(c, new StringTokenizer(br.readLine()));
        
        for (int i = 0; i <= 10001; i++) {
        	Arrays.fill(dp[i], -1);
			if (dfs(i, 0) >= m) {
				System.out.println(i);
				break;
			}
		}
	}
	
	private static int dfs(int cost, int idx) {
		if (dp[cost][idx] != -1) return dp[cost][idx];
		if (idx == n) return dp[cost][idx] = 0;

		if (cost < c[idx]) return dp[cost][idx] = dfs(cost, idx + 1);
		else return dp[cost][idx] = Math.max(
				dfs(cost, idx + 1), 
				dfs(cost - c[idx], idx + 1) + a[idx]);
	}
	
	private static int initArr(int[] arr, StringTokenizer st) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		return sum;
	}
}

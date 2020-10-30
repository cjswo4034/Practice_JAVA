package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 행렬곱셈순서_11049 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        int[][] dp = new int[n + 1][n + 1];
        int[][] arr = new int[n + 1][2];
        
        for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(dp[i], 987654321);
			dp[i - 1][i] = arr[i - 1][0] * arr[i][0] * arr[i][1];
			dp[i][i] = 0;
		}
        
        for (int size = 2; size < n; size++) {
			for (int pos = 1; pos + size <= n; pos++) {
				for (int i = pos; i < pos + size; i++) {
					dp[pos][pos + size] = Math.min(
							dp[pos][pos + size],
							dp[pos][i] + dp[i + 1][pos + size] + arr[pos][0] * arr[i][1] * arr[pos + size][1]);
				}
			}
		}
        
        System.out.println(dp[1][n]);
	}
}

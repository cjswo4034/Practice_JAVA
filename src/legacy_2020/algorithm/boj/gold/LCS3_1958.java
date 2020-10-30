package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS3_1958 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] str = new char[3][];
		str[0] = br.readLine().toCharArray();
		str[1] = br.readLine().toCharArray();
		str[2] = br.readLine().toCharArray();

		System.out.println(lcs(str));
	}
	
	// dp[i][j][k] = 
	// 1번째 문자열의 0부터 i까지 
	// 2번째 문자열 0부터 j까지
	// 3번째 문자열 0부터 k까지의 LCS
	static int lcs(char[][] str) {
		int res = 0;
		int[][][] dp = new int[str[0].length + 1][str[1].length + 1][str[2].length + 1];
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				for (int k = 1; k < dp[0][0].length; k++) {
					if (str[0][i - 1] == str[1][j - 1] && str[1][j - 1] == str[2][k - 1])
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					else dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
					res = Math.max(res, dp[i][j][k]);
				}
			}
		}
		return res;
	}
}

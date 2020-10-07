package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _3316_x {
	static final int VALUE = 1000000007;
	static int result;
	static int[][] dp;
	static char[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			array = br.readLine().toCharArray();
			dp = new int[array.length + 1][16 + 1];
			dp[0][1] = 1;

			for (int i = 1; i <= array.length; i++) {
				int c = 1 << array[i - 1] - 'A';

				for (int j = 1; j <= 16; j++) {
					if ((c & j) == 0) continue;

					for (int k = 1; k <= 16; k++) {
						if ((j & k) == 0) continue;

						dp[i][j] += dp[i - 1][k];
						dp[i][j] %= VALUE;
					}
				}
			}

			result = 0;
			for (int i = 1; i <= 16; i++) {
				result += dp[array.length][i];
				result %= 1000000007;
			}
			
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
	}
}

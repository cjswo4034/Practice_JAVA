package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _5293 {
	static String result;

	static String[][][][][] dp = new String[21][21][21][21][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		dp[1][0][0][0][0] = "00";
		dp[0][1][0][0][1] = "01";
		dp[0][0][1][0][0] = "10";
		dp[0][0][0][1][1] = "11";

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			result = null;

			//solve("0", count[1], count[2], count[3], count[4]);
			//solve("1", count[1], count[2], count[3], count[4]);
			
			result = dp[a][b][c][d][0] != null ? dp[a][b][c][d][0] : dp[a][b][c][d][1];

			if (result == null) {
				bw.write("#" + t + " " + "impossible" + "\n");
			} else {
				bw.write("#" + t + " " + result + "\n");
			}
		}
		bw.flush();
	}

	static void solve(String str, int a, int b, int c, int d) {
		if (result != null)
			return;
		if (a < 0 || b < 0 || c < 0 || d < 0)
			return;
		if (a == 0 && b == 0 && c == 0 && d == 0) {
			result = str;
			return;
		}

		if (str.substring(str.length() - 1).equals("0")) {
			solve(str + "0", a - 1, b, c, d);
			solve(str + "1", a, b - 1, c, d);
			return;
		}
		solve(str + "0", a, b, c - 1, d);
		solve(str + "1", a, b, c, d - 1);
	}
}

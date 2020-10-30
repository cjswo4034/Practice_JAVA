package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1865_2 {
	static int n;
	static double result;
	static double[][] input;
	static double[][] cash = new double[16][1 << 16];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			input = new double[n][n];

			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					input[i][j] = Double.parseDouble(st.nextToken()) / 100;
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0, leng = 1 << 16; j < leng; j++) {
					cash[i][j] = -1;
				}
			}
			
			for (int i = 0; i < n; i++) {
				cash[0][1 << i] = input[0][i];
			}
			
			for (int i = 1; i < n; i++) {
				for (int j = 0, leng = 1 << 16; j < leng; j++) {
					if(cash[i - 1][j] == -1) continue;
					for (int k = 0; k < n; k++) {
						int chk = 1 << k;
						if((chk & j) == 1) continue;
						cash[i][chk | j] = Math.max(cash[i][chk | j],
								 					cash[i - 1][j] * input[i][k]);
					}
				}
			}
			
			result = cash[n - 1][(1 << n) - 1] * 100;
			
			
			bw.write("#" + t + " " + String.format("%.6f", result) + "\n");
			bw.flush();
		}
	}
}
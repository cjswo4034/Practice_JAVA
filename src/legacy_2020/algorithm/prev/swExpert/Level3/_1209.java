package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1209 {
	static int result;
	static int[][] map = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}	
			}
			
			result = 0;
			solve();			
			
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
	}

	static void solve() {
		int sumLR = 0;
		int sumRL = 0;
		for (int i = 0; i < 100; i++) {
			int sumRow = 0;
			int sumCol = 0;
			for (int j = 0; j < 100; j++) {
				sumRow += map[i][j];
				sumCol += map[j][i];
			}
			sumLR += map[i][i];
			sumRL += map[99 - i][i];
			
			result = Math.max(result, Math.max(sumRow, sumCol));			
		}
		result = Math.max(result, Math.max(sumLR, sumRL));
	}
}

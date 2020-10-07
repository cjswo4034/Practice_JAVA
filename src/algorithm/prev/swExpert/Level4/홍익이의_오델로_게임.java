package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 홍익이의_오델로_게임 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int[] row, col;
		boolean[][] map;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1, n; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			n = Integer.parseInt(br.readLine());
			row = new int[n];
			col = new int[n];
			map = new boolean[n][n];
			
			String input;
			for (int i = 0; i < n; i++) {
				input = br.readLine();
				for (int j = 0; j < n; j++) {
					if (input.charAt(j) == 'B') {
						row[i]++;
						col[j]++;
						map[i][j] = true;
					}
				}				
			}

			int sum, result = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sum = row[i] + col[j];
					if (map[i][j]) sum--;
					if (sum % 2 == 1) result++;
				}				
			}
			
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}

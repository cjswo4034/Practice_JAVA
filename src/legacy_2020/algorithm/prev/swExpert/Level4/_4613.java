package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _4613 {
	static int n, m, result;
	static int[] color;
	static int[][] stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			color = new int[n];
			stack = new int[n][3];

			char[] map;
			for (int i = 0; i < n; i++) {
				map = br.readLine().toCharArray();
				for (int j = 0; j < m; j++) {
					if (map[j] == 'W') {
						stack[i][0]++;
					} else if (map[j] == 'B') {
						stack[i][1]++;
					} else {
						stack[i][2]++;
					}
				}
			}
			
			result = Integer.MAX_VALUE;
			for (int i = 0; i < n - 1; i++) {
				for (int j = 1, leng = n - i - 1; j < leng; j++) {
					result = Math.min(result, sum(j, i + j));
				}
			}

			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
	
	static int sum(int from, int to) {
		int sum = 0;
		
		for (int i = 0; i < from; i++) {
			sum += (m - stack[i][0]);
		}
		
		for (int i = from; i <= to; i++) {
			sum += (m - stack[i][1]);
		}
		
		for (int i = to + 1; i < n; i++) {
			sum += (m - stack[i][2]);
		}
		
		return sum;
	}
}
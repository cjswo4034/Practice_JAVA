package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1494 {
	static int n;
	static int[] arrayX, arrayY;
	static long result;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			arrayX = new int[n + 1];
			arrayY = new int[n + 1];
			check = new boolean[n + 1];

			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				arrayX[i] = Integer.parseInt(st.nextToken());
				arrayY[i] = Integer.parseInt(st.nextToken());
			}

			result = Long.MAX_VALUE;
			dfs(0, 0);

			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}

	static void dfs(int ptr, int depth) {
		if (depth == n / 2) {
			long x = 0, y = 0, sum = 0;
			for (int i = 0; i < n; i++) {
				if (!check[i]) {
					x += arrayX[i];
					y += arrayY[i];
				} else {
					x -= arrayX[i];
					y -= arrayY[i];
				}
			}

			sum = x * x + y * y;
			if (result > sum) {
				result = sum;
			}
			return;
		}
		
		for (int i = depth; i < n; i++) {
			if (!check[i]) {
				check[i] = true;
				dfs(i + 1, depth + 1);
				check[i] = false;
			}
		}
	}
}

package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2817 {
	static boolean[] check = new boolean[21];
	static int[] array = new int[21];
	static int n, k, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			array = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}

			DFS(0, 0);
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	public static void DFS(int depth, int sum) {
		if (sum == k) {
			result++;
			return;
		}

		if (depth >= n || sum > k) {
			return;
		}

		DFS(depth + 1, sum + array[depth]);
		DFS(depth + 1, sum);
	}

	public static void bitMask() {
		for (int num = 0; num < (1 << n); num++) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if ((num & (1 << i)) != 0) {
					sum += array[i];
					if (sum > k)
						break;
				}
			}
			if (sum == k)
				result++;
		}
	}

}
package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _3347 {
	static int n, m, max, result;
	static int[] array, vote;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			array = new int[n];
			vote = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}

			max = result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int cur = Integer.parseInt(st.nextToken());
				for (int j = 0; j < m; j++) {
					if(cur >= array[j]) {
						vote[j]++;
						if(vote[j] > max) {
							max = vote[j];
							result = j + 1;
						}
						break;
					}
				}
			}

			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
}

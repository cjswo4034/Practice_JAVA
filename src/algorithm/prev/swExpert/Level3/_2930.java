package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _2930 {
	static int n;
	static PriorityQueue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			n = Integer.parseInt(br.readLine());
			q = new PriorityQueue<>(Collections.reverseOrder());

			int num;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				if (st.hasMoreTokens()) {
					num = Integer.parseInt(st.nextToken());
					q.add(num);
				} else {
					if (!q.isEmpty()) {
						sb.append(q.poll() + " ");
					} else {
						sb.append(-1 + " ");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

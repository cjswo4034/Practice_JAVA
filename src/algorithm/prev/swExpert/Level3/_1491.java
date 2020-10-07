package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1491 {
	static int n, a, b;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			long result = Integer.MAX_VALUE;
			long tmp = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				for(int j = 1; j * i <= n ; j++) {
					tmp = a * Math.abs(i - j) + b * (n - i * j);
					result = Math.min(result, tmp);
				}
			}
			
			sb.append(result);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

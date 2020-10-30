package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6019 {
	static int d, a, b, f;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			
			float ans = ((float)d / (a + b)); 
			ans *= f;
			
			sb.append(ans);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

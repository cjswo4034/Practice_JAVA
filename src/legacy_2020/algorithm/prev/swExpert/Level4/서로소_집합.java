package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 서로소_집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int[] union;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
	
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int a, b, c;
			
			union = new int[n];
			Arrays.fill(union, -1);
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;
				
				if (a == 0) merge(union, b, c);
				else {
					b = find(union, b);
					c = find(union, c);
					
					if (b == c) sb.append(1);
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static int find(int[] union, int a) {
		if (union[a] == -1) return a;
		return union[a] = find(union, union[a]);
	}
	
	private static boolean merge(int[] union, int a, int b) {
		int a_ = find(union, a);
		int b_ = find(union, b);
		if (a_ == b_) return false;
		union[b_] = a_;
		return true;
	}
}

package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1217 {
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			result = solve(n, m);			
			
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
	}

	static int solve(int n, int m) {
		if(m == 1) return n;
		return n * solve(n, m - 1); 
	}
}

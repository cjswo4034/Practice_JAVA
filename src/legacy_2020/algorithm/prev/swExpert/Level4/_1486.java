package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1486 {
	static int n, b, result;
	static int[] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			array = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				result += array[i];
			}
			
			Arrays.sort(array);
			dfs(0, 0);
			
			bw.write("#" + t + " " + (result - b) + "\n");
			bw.flush();
		}
	}
	
	static void dfs(int depth, int sum) {
		if(sum >= b) {
			if(result > sum) {
				result = sum;
			}
			return;
		}
		
		if(n == depth) {
			return;
		}
		dfs(depth + 1, sum);
		dfs(depth + 1, sum + array[depth + 1]);
	}
}

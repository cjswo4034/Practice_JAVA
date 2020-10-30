package algorithm.boj.sliver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 경로찾기_11403 {
	static int[][] map, result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		result = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) dfs(i, i);	
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void dfs(int root, int current) {
		for (int i = 0; i < map.length; i++) {
			if (map[current][i] == 1 && result[root][i] == 0) {
				result[root][i] = 1;
				dfs(root, i);
			}
		}
	}
}

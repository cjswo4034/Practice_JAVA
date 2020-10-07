package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _2806 {
	static int n, result;
	static boolean[][] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			result = 0;
			check = new boolean[n + 1][n + 1];
			for (int i = 0; i < n; i++) {
				check[0][i] = true;
				dfs(0);
				check[0][i] = false;
			}
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
	
	static void dfs(int depth) {
		if(depth == n - 1) {
			//print();
			result++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(check(depth + 1, i)) {
				check[depth + 1][i] = true;
				dfs(depth + 1);
				check[depth + 1][i] = false;
			}
		}
	}
	
	static boolean check(int x, int y) {
		boolean result = true;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == x && j == y) continue;
				if(check[i][j]) {
					int w = Math.abs(j - y);
					int h = Math.abs(i - x);
					if(i == x || j == y) {
						result = false;
					}
					if(w == h) {
						result = false;
					}
				}
			}
		}
		
		return result;
	}
	
	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(check[i][j] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

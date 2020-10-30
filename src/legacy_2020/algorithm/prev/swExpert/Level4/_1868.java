package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _1868 {
	static int n, result;
	static char[][] map;
	static boolean[][] check;

	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
							{ 1, -1 }, { -1, 1 }, { 1, 1 }, { -1, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());

			map = new char[n][n];
			check = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();

			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == '*') {
						check(i, j);
						check[i][j] = true;
					}
				}
			}

			result = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == '.') {
						result++;
						map[i][j] = '0';
						check[i][j] = true;
						dfs(new Pair(i, j));
					}
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(!check[i][j]) {
						result++;
					}
				}
			}
			
			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
	}
	
	static void dfs(Pair p) {
		for (int i = 0; i < 8; i++) {
			int nx = p.x + dir[i][0];
			int ny = p.y + dir[i][1];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n)
				continue;

			if(!check[nx][ny]) {
				check[nx][ny] = true;
				if(map[nx][ny] == '.') {
					map[nx][ny] = '0';
					dfs(new Pair(nx, ny));
				}
			}
		}
	}

	static void check(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx < 0 || ny < 0 || nx >= n || ny >= n)
				continue;

			if (map[nx][ny] == '.') {
				map[nx][ny] = '1';
			} else if (map[nx][ny] != '*') {
				map[nx][ny]++;
			}

		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

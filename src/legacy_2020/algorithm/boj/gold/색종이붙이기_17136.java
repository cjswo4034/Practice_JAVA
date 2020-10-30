package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {
	static int cnt, result = 987654321;
	static int[] colors = {0, 5, 5, 5, 5, 5};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int map[][] = new int[10][10];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(map, 0, 0);
		if (result == 987654321) result = -1;
		System.out.println(result);
	}
	
	static void dfs(int[][] map, int r, int c) {
		if (cnt >= result) return;
		if (c == 10) {
			dfs(map, r + 1, 0);
			return;
		}
		if (r == 10) {
			result = cnt;
			return;
		}
		if (map[r][c] == 0) {
			dfs(map, r, c + 1);
			return;
		}
		
		for (int i = 5; i > 0; i--) {
			if (colors[i] == 0 || r + i > 10 || c + i > 10) continue;
			boolean flag = true;
			for (int j = 0; j < i && flag; j++) {
				for (int k = 0; k < i; k++) {
					if (map[r + j][c + k] == 0) {
						flag = false;
						break;
					}
				}
			}
			if(!flag) continue;
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < i; k++) {
					map[r+j][c+k] = 0;
				}
			}
			--colors[i];
			++cnt;
			dfs(map, r, c + i);
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < i; k++) {
					map[r+j][c+k] = 1;
				}
			}
			++colors[i];
			--cnt;
		}
	}
	
	static void fill(int[][] map, int color, int r, int c) {
		for (int i = r; i < r + color; i++) {
			for (int j = c; j < c + color; j++) {
				map[i][j] = color;
			}
		}
	}
	
	static boolean fillable(int[][] map, int color, int r, int c) {
		if (colors[color] <= 0) return false;
		for (int i = r; i < r + color; i++) {
			for (int j = c; j < c + color; j++) {
				if (map[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	static void bfs(int[][] map, boolean[][] visited, Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			Point curr = q.poll();
//			for (int[] d: direction) {
//				int nx = curr.x + d[0];
//				int ny = curr.y + d[1];
//				if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) continue;
//				if (visited[nx][ny] || map[nx][ny] == 0) continue;
//				visited[nx][ny] = true;
//				q.add(new Point(nx, ny));
//			}
		}
	} 
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}

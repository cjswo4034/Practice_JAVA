package algorithm.prev.programmers.Level2;

import java.util.LinkedList;
import java.util.Queue;

public class _1829 {
	static boolean check[][] = new boolean[101][101];
	static int map[][] = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
			{ 0, 0, 0, 3 } };
	static int ans = 0;
	static int cnt = 0;
	static int n, m;

	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static int bfs(Pair p) {
		Queue<Pair> q = new LinkedList<>();
		q.add(p);
		check[p.x][p.y] = true;

		int size = 0;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			size++;

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];

				if (nx >= n || nx < 0 || ny >= m || ny < 0)
					continue;

				if (!check[nx][ny] && map[cur.x][cur.y] == map[nx][ny]) {
					q.add(new Pair(nx, ny));
					check[nx][ny] = true;
				}
			}
		}
		
		return size;
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		n = 6;
		m = 4;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(!check[i][j]) {
					Pair p = new Pair(i, j);
					int size = bfs(p);
					if(map[i][j] != 0) {
						cnt++;
						ans = Math.max(size, ans);
					}
				}
			}
		}
		System.out.println(cnt);
		System.out.println(ans);
	}
}

package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 불_4179 {
	static int n, m;
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		map = new char[n][m];
		
		Queue<Node> q = new LinkedList<>();
		
		int sr = 0, sc = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == '#' || map[i][j] == '.') continue;
				if (map[i][j] == 'J') {
					sr = i;
					sc = j;
				} else q.add(new Node(i, j, true));
			}
		}
		
		// 불이 먼저 이동하고 그 뒤 진수가 이동한다.
		q.add(new Node(sr, sc, false));
		
		int answer = bfs(q);
		if (answer == -1) System.out.println("IMPOSSIBLE");
		else System.out.println(answer);
	}
	
	static int bfs(Queue<Node> q) {
		int res = 0;
		
		while (!q.isEmpty()) {
			res++;
			int size = q.size();
			while (size-- > 0) {
				Node curr = q.poll();
				if (!curr.isFire && (curr.r == 0 || curr.r == n - 1 || curr.c == 0 || curr.c == m - 1)) return res;
				
				for (int i = 0; i < 4; i++) {
					int nr = curr.r + dir[i][0];
					int nc = curr.c + dir[i][1];
					
					if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
					if (map[nr][nc] == '#') continue;
					
					// 불은 벽과 불을 제외하고 모두 이동할 수 있다.
					if (curr.isFire && map[nr][nc] == 'F') continue;
					
					// 진수는 .일 때만 이동할 수 있다.
					if (!curr.isFire && map[nr][nc] != '.') continue;
					
					map[nr][nc] = curr.isFire ? 'F' : 'J';
					q.add(new Node(nr, nc, curr.isFire));
				}
			}
		}
		
		return -1;
	}
	
	static class Node {
		int r, c;
		boolean isFire;
		public Node(int r, int c, boolean isFire) {
			this.r = r;
			this.c = c;
			this.isFire = isFire;
		}
		
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", isFire=" + isFire + "]";
		}
	}
}

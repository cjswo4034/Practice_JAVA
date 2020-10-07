package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고_이동하기_2_14442 {
	static int n, m, k;
	static int[][] map, visited;
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        visited = new int[n][m];
        String line;
        for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
        
        System.out.println(bfs());
	}
	
	static int bfs() {
		int res = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		
		visited[0][0] = 0;
		
		while (!q.isEmpty()) {
			res++;
			int size = q.size();
			while(size-- > 0) {
				Node curr = q.poll();
				if (curr.row + 1 == n && curr.col + 1 == m) return res;
				
				for (int i = 0; i < 4; i++) {
					int nr = curr.row + dir[i][0];
					int nc = curr.col + dir[i][1];
					if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
					
					int nk = curr.cnt + map[nr][nc];
					if (nk > k || visited[nr][nc] <= nk) continue;
					
					visited[nr][nc] = nk;
					q.add(new Node(nr, nc, nk));
				}
			}
		}
		
		return -1;
	}
	
	static class Node {
		int row, col, cnt;

		public Node(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
}

package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 구슬탈출2_13460 {
	static int n, m, result = 987654321;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static char[][] map;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        
        Pair startR = null, startB = null, hole = null;
        for (int i = 0; i < n; i++) {
        	String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '.' || map[i][j] == '#') continue;
				if (map[i][j] == 'O') {
					hole = new Pair(i, j);
					continue;
				}
				if (map[i][j] == 'R') startR = new Pair(i, j);
				if (map[i][j] == 'B') startB = new Pair(i, j);
				map[i][j] = '.';
			}
		}
        
        System.out.println(bfs(startR, startB, hole));
	}
	// 1. 이동 방법 (빨간 공과 파란 공이 같은 행, 렬에 있고 같은 방향으로 움직일 때 우선순위)
	//    a) 네 방향 탐색하고 빨간 공, 파란 공에 대해서 각각 벽이나 구멍, 다른 공을 만날 때 까지 더함
	//       - R, B가 같은 행이나 열에 있으면서 해당 행, 열로 움직일 때 먼저 움직일 공의 방향을 정함(ex. R:[1, 3], B[1, 5], d:Left 일 때 R[1] < B[1] 이므로 R먼저 움직임)
	// 2. 중복 체크
	// 	  a) 이동한 방향으로 체크
	//		 R(r1, c1), B(r2, c2)라고 할 때, [r1][c1].contains([r2,c2]) -> X
	
	static int bfs(Pair startR, Pair startB, Pair hole) {
		int res = 0;
		Set<Pair>[][] visit = new HashSet[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				visit[i][j] = new HashSet<>();
		
		Queue<Pair> q = new LinkedList<>();
		q.add(startR);
		q.add(startB);
		visit[startR.r][startR.c].add(startB);
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size > 0) {
				Pair cr = q.poll();
				Pair cb = q.poll();
				size -= 2;

				if (cr.r == hole.r && cr.c == hole.c) return res; 
				
				for (int i = 0; i < 4; i++) {
					int nrR = cr.r + dir[i][0], ncR = cr.c + dir[i][1];
					int nrB = cb.r + dir[i][0], ncB = cb.c + dir[i][1];
					if (i % 2 == 0) {
						while (ncR > 1 && ncR < m - 1 && map[nrR][ncR] == '.') ncR += dir[i][1];
						while (ncB > 1 && ncB < m - 1 && map[nrB][ncB] == '.') ncB += dir[i][1];
						
						if (ncR >= 0 && ncR < m && map[nrR][ncR] == '#') ncR -= dir[i][1];
						if (ncB >= 0 && ncB < m && map[nrB][ncB] == '#') ncB -= dir[i][1];
						
						if (map[nrB][ncB] == 'O') continue;
						
						if (nrR == nrB && ncR == ncB) {
							if (cr.c < cb.c) {
								if (i == 2) ncB++;
								else ncR--;
							} else {
								if (i == 2) ncR++;
								else ncB--;
							}
						}
					} else {
						while (nrR > 0 && nrR < n && map[nrR][ncR] == '.') nrR += dir[i][0];
						while (nrB > 0 && nrB < n && map[nrB][ncB] == '.') nrB += dir[i][0];
						
						if (nrR >= 0 && nrR < n && map[nrR][ncR] == '#') nrR -= dir[i][0];
						if (nrB >= 0 && nrB < n && map[nrB][ncB] == '#') nrB -= dir[i][0];
						
						if (map[nrB][ncB] == 'O') continue;
						
						if (nrR == nrB && ncR == ncB) {
							if (cr.r < cb.r) {
								if (i == 1) nrR--;
								else nrB++;
							} else {
								if (i == 1) nrB--;
								else nrR++;
							}
						}
					}
					if (nrR <= 0 || ncR <= 0 || nrR >= n - 1 || ncR >= m - 1) continue;
					if (nrB <= 0 || ncB <= 0 || nrB >= n - 1 || ncB >= m - 1) continue;
					
					Pair nR = new Pair(nrR, ncR);
					Pair nB = new Pair(nrB, ncB);
					
					if (visit[nrR][ncR].contains(nB)) continue;
					
					visit[nrR][ncR].add(nB);
					
					q.add(nR);
					q.add(nB);
				}
			}
			res++;
			if (res == 11) break;
		}
		return -1;
	}
	
	static class Pair {
		int r, c;
		boolean isRed;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(r, c);
		}

		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair)obj;
			return r == p.r && c == p.c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + ", isRed=" + isRed + "]";
		}
	}
}

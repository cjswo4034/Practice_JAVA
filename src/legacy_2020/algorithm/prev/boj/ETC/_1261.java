package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1261 {
	static int n, m;
	static int map[][], dist[][];
	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dist = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			char[] tmp = br.readLine().toCharArray();
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			for (int j = 0, leng = tmp.length; j < leng; j++) {
				map[i][j] = tmp[j] - '0';
			}
		}

		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.add(new Pair(0,0,0)); // ����ġ, x ��ǥ
		dist[0][0] = 0;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];
								
				if(nx >= n || ny >= m || 0 > nx || 0 > ny) continue;
				
				if(dist[nx][ny] > dist[p.x][p.y] + map[p.x][p.y]) {
					dist[nx][ny] = dist[p.x][p.y] + map[p.x][p.y];
					q.add(new Pair(nx, ny, dist[nx][ny]));
				}
			}
		}
		
		System.out.println(dist[n-1][m-1]);
	}
	
	static class Pair implements Comparable<Pair>{
		int x, y, w;
		Pair(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
		
		public int compareTo(Pair a) {
			return this.w <= a.w ? -1 : 1;
		}		
	}	
}

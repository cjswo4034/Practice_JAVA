package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _4485 {
	static int n;
	static int map[][], dist[][];
	static int dir[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
    static final int INF = 100000000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int idx = 0;
		while(true) {
			idx++;
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				return;
			map = new int[n][n];
			dist = new int[n][n];
			
			for(int i = 0 ; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < n ;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;
				}
			}
						
			dijstra();
			
			System.out.println("Problem " + idx + ": " + dist[n - 1][n - 1]);
		}
	}
	
	static void dijstra() {
		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.add(new Pair(0,0,map[0][0]));

		dist[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			if(dist[p.x][p.y] != p.w) continue;

			for(int i = 0 ; i < 4 ; i++) {
				int nx = p.x + dir[i][0];
				int ny = p.y + dir[i][1];
				
				if(nx >= n || ny >= n || 0 > nx || 0 > ny) continue;
				
				if(dist[nx][ny] > dist[p.x][p.y] + map[nx][ny]) {
					dist[nx][ny] = dist[p.x][p.y] + map[nx][ny];
					q.add(new Pair(nx, ny, dist[nx][ny]));
				}
			}
		}
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

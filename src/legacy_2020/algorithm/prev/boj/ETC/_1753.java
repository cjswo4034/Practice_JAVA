package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1753 {
	static final int Max = Integer.MAX_VALUE;
	static int dist[] = new int[2000];
	static int prev[] = new int[2000];
	static boolean visited[] = new boolean[2000];
	static ArrayList<Pair> list[];
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v, e, k, u, w, y;
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine()) - 1;
		list = new ArrayList[v];
		
		for(int i = 0 ; i < v; i++) {
			list[i] = new ArrayList<>();
			dist[i] = Max;
		}
		
		for(int i = 0 ; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			list[u-1].add(new Pair(y-1,w));
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(k);
		dist[k] = 0;

		while(!q.isEmpty()) {
			int curr;
			do {
				curr = q.poll();
			}while(!q.isEmpty() && visited[curr]);

			if(visited[curr]) break;
			
			visited[curr] = true;
			
			for(Pair p : list[curr]) {
				if(dist[p.y] > dist[curr] + p.w) {
					dist[p.y] = dist[curr] + p.w;
					prev[p.y] = curr;
					q.add(p.y);
				}
			}
		}
		
		for(int i = 0 ; i < v ; i++) {
			if(dist[i] == Max) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}
	
	
	static class Comp implements Comparator<Pair>{
		@Override
		public int compare(Pair a, Pair b) {
			if(a.w == b.w)
				return a.y < b.y ? -1 : 1;
			return a.w > b.w ? -1 : 1;
		}
	}
	
	static class Pair{
		int y, w;
		Pair(int y, int w){
			this.y = y;
			this.w = w;
		}
	}

}

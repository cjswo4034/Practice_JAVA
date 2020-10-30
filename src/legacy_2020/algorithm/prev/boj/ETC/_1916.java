package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1916 {
	static int n, m, start, end;
	static int dist[];
	static final int Max = Integer.MAX_VALUE;
	static boolean visited[];
	static ArrayList<Pair> list[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		dist = new int[n];
		list = new ArrayList[n];
		visited = new boolean[n];
		for(int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList<>();
			dist[i] = Max;
		}
		
		int v, e, w;
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list[v - 1].add(new Pair(e - 1, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Comp comp = new Comp();
		PriorityQueue<Pair> q = new PriorityQueue<>(1, comp);
		q.add(new Pair(start - 1, 0));
		dist[start - 1] = 0;
		
		while(!q.isEmpty()) {
			int curr;
			do {
				curr = q.poll().y;
			}while(!q.isEmpty() && visited[curr]);
			
			if(visited[curr]) break;
			
			visited[curr] = false;
			
			for(int i = 0, leng = list[curr].size(); i < leng ; i++) {
				int next = list[curr].get(i).y;
				int weight = list[curr].get(i).w;
				if(dist[next] > dist[curr] + weight) {
					dist[next] = dist[curr] + weight;
					q.add(new Pair(next, dist[next]));
				}
			}
		}
		
		System.out.println(dist[end - 1]);
	}
	
	static class Comp implements Comparator<Pair>{
		@Override
		public int compare(Pair o1, Pair o2) {
			if(o1.w == o2.w)
				return o1.y < o1.y ? -1 : 1;
			return o1.w < o1.w ? -1 : 1;
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

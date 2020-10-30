package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1504 {
	static int n, e, one, two;
	static int dist[] = new int[800];
	static boolean visited[];
	static ArrayList<Pair> [] list = new ArrayList[800];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < e ; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a - 1].add(new Pair(b - 1, c));
			list[b - 1].add(new Pair(a - 1, c));
		}
		
		st = new StringTokenizer(br.readLine());
		one = Integer.parseInt(st.nextToken()) - 1;
		two = Integer.parseInt(st.nextToken()) - 1;
		
		long ans = distra(one, two);
		ans += Math.min(distra(0, one) + distra(two, n - 1), distra(0, two) + distra(one, n - 1));
		
		System.out.println(ans >= Integer.MAX_VALUE ? -1 : ans);
	}
	
	static long distra(int start, int end) {
		PriorityQueue<Pair> q = new PriorityQueue<>(1, new Comp());
		visited = new boolean[n];
		q.add(new Pair(start, 0));
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			int curr;
			do {
				curr = q.poll().y;
			}while(!q.isEmpty() && visited[curr]);
			
			if(visited[curr]) break;
			
			visited[curr] = true;
			
			for(int i = 0, leng = list[curr].size() ; i < leng ; i++) {
				int next = list[curr].get(i).y;
				int weight = list[curr].get(i).w;
				if(dist[next] > dist[curr] + weight) {
					dist[next] = dist[curr] + weight;
					q.add(new Pair(next, dist[next]));
				}
			}
		}
		return dist[end];
	}
	
	static class Comp implements Comparator<Pair>{
		@Override
		public int compare(Pair a, Pair b) {
			if(a.w == b.w)
				return a.y < b.y ? -1 : 1;
			return a.w < b.w ? -1 : 1;
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

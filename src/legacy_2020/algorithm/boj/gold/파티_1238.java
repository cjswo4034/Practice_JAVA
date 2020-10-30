package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티_1238 {
	static int n, m, x;
	static int[] res;
	static List<Node>[] lists1, lists2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		res = new int[n];
		lists1 = new ArrayList[n];	
		lists2 = new ArrayList[n];		
		
		for(int i = 0 ; i < n ; i++) {
			lists1[i] = new ArrayList<>();
			lists2[i] = new ArrayList<>();
		}
		
		int a, b, c;
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			lists1[a - 1].add(new Node(b - 1, c));
			lists2[b - 1].add(new Node(a - 1, c));
		}

		dijstra(x - 1, lists1);
		dijstra(x - 1, lists2);
		
		int ans = 0;
		for(int i = 0 ; i < n ; i++) {
			ans = Math.max(ans, res[i]);
		}
		
		System.out.println(ans);
	}
	
	static void dijstra(int start, List<Node>[] list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int v = curr.idx;
			int w = curr.weight;
			
			if (w > dist[v]) continue;
			
			for (Node next: list[v]) {
				int nv = next.idx;
				int nw = next.weight;
				
				if (dist[nv] > dist[v] + nw) {
					dist[nv] = dist[v] + nw;
					pq.add(new Node(nv, dist[nv]));
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			res[i] += dist[i];
		}
	}
	
	static class Node implements Comparable<Node>{
		int idx, weight;
		
		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}

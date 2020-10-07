package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티2_1238 {
	static int n, m, x;
	static int[] result;
	static List<Node>[] list1, list2;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        result = new int[n + 1];
        list1 = new ArrayList[n + 1];
        list2 = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
        	list1[i] = new ArrayList<>();
        	list2[i] = new ArrayList<>();
		}
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());;
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list1[from].add(new Node(to, weight));
			list2[to].add(new Node(from, weight));
		}
        
        djistra(list1, x);
        djistra(list2, x);
        
        int ans = 0;
        for (int res : result) ans = Math.max(ans, res);
        
        System.out.println(ans);
	}
	
	static void djistra(List<Node>[] list, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] cost = new int[n + 1];
		
		Arrays.fill(cost, Integer.MAX_VALUE);
		pq.add(new Node(start, 0));
		cost[start] = 0;
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int v = curr.to;
			int w = curr.weight;
			
			if (w > cost[v]) continue;
			
			for (Node n : list[v]) {
				int nv = n.to;
				int nw = cost[v] + n.weight;
				
				if (cost[nv] >= nw) {
					cost[nv] = nw;
					pq.add(new Node(nv, nw));
				}
			}
		}
		
		for (int i = 0; i < cost.length; i++)
			result[i] += cost[i];	
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
}

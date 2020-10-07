package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 최소비용구하기2_11779 {
	static int n, m, from, to, count = 1;
	static int[] dist, pre;
	static List<Integer> route = new ArrayList<>();
	static List<Integer> answer;
	static List<Node>[] arr;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Node> pq = new PriorityQueue<>(); 
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        pre = new int[n];
        dist = new int[n];
        arr = new ArrayList[n];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) arr[i] = new ArrayList<>();
        for (int i = 0, f, t, w; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken()) - 1;
			t = Integer.parseInt(st.nextToken()) - 1;
			w = Integer.parseInt(st.nextToken());
			arr[f].add(new Node(t, w));
		}
        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken()) - 1;
        to = Integer.parseInt(st.nextToken()) - 1;

        dist[from] = 0;
        pq.add(new Node(from, 0));
        while (!pq.isEmpty()) {
        	Node curr = pq.poll();
        	
        	for (Node next: arr[curr.to]) {
				int nv = next.to;
				int nw = curr.weight + next.weight;
				
				if (dist[nv] > nw) {
					pre[nv] = curr.to;
					dist[nv] = nw;
					pq.add(new Node(nv, nw));
				}
			}
        }

        dfs(sb, to);
        sb.insert(0, count + "\n").insert(0, dist[to] + "\n");
        System.out.println(sb);
	}
	
	static void dfs(StringBuilder sb, int v) {
		if (v == from) {
			sb.append(v + 1);
			return;
		}
		dfs(sb, pre[v]);
		sb.append(" ").append(v + 1);
		count++;
	}
	
	static class Node implements Comparable<Node>{
		int to, weight;
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.weight == o.weight) return Integer.compare(this.to, o.to); 
			return Integer.compare(this.weight, o.weight);
		}
	}
}

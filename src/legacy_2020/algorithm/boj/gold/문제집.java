package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문제집 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Node> pq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[n];
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
        	nodes[i] = new Node(i);
        	adj[i] = new ArrayList<>();
        }
        for (int i = 0, a, b; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	
        	nodes[b - 1].indegree++;
        	adj[a - 1].add(b - 1);
		}
        
        for (Node node: nodes)
        	if (node.indegree == 0) pq.add(node);
        
        while (!pq.isEmpty()) {
        	Node node = pq.poll();
        	
        	sb.append(node.idx + 1).append(" ");
        	
        	for (int next: adj[node.idx]) {
        		nodes[next].indegree--;
        		if (nodes[next].indegree == 0)
        			pq.add(nodes[next]);
        	}
        }

        System.out.println(sb);
	}
	
	static class Node implements Comparable<Node>{
		int idx, indegree;
		
		public Node(int idx) {
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Node o) {
			if (indegree == o.indegree) return Integer.compare(idx, o.idx);
			return Integer.compare(indegree, o.indegree);
		}
	}
}

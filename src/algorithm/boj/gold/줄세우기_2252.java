package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄세우기_2252 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> pq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] indegree = new int[n];
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0, a, b; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	
        	indegree[b - 1]++;
        	adj[a - 1].add(b - 1);
		}
        
        for (int i = 0; i < n; i++)
			if (indegree[i] == 0) pq.add(i);

        while (!pq.isEmpty()) {
        	int node = pq.poll();
        	
        	sb.append(node + 1).append(" ");
        	
        	for (int next: adj[node]) {
        		if (--indegree[next] == 0) pq.add(next);
        	}
        }

        System.out.println(sb);
	}
}

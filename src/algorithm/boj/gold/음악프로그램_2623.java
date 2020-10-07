package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음악프로그램_2623 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int size = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] indegree = new int[n + 1];
        List<Integer>[] adj = new ArrayList[n + 1];
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<Integer>();
        for (int i = 0, j, p; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
			j = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			for (int k = 1, c; k < j; k++) {
				c = Integer.parseInt(st.nextToken());
				adj[p].add(c);
				indegree[c]++;
				p = c;
			}
		}
        
        for (int i = 1; i <= n; i++) 
        	if (indegree[i] == 0) q.add(i);
        
        while (!q.isEmpty()) {
        	int current = q.poll();
        	
        	sb.append(current).append("\n");
        	size++;
        	
        	for (int next: adj[current]) {
        		if (--indegree[next] == 0) q.add(next);
        	}
        }
        
        if (size != n) sb = new StringBuilder("0");
        System.out.println(sb);
	}
}

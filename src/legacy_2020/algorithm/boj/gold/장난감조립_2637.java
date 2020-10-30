package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 장난감조립_2637 {
	static List<Integer> defaultComponents = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int [] needs = new int[n + 1];
        
        Queue<Component> q = new LinkedList<>();
        Component[] components = new Component[n + 1];
        List<Integer> [] adj = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
        	adj[i] = new ArrayList<>();
			components[i] = new Component(i, n + 1);
		}
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			adj[y].add(x);
			components[x].indegree++;
			components[x].needs[y] = z;
		}
        
        for (Component c: components) {
        	if (c != null && c.indegree == 0) {
        		q.add(c);
        		defaultComponents.add(c.idx);
        	}
        }
        
        for (int i = 0; i < n; i++) {
			Component c = q.poll();
			
			add(c.needs, needs, 1);

			for (int next: adj[c.idx]) {
				if (--components[next].indegree == 0)
					q.add(components[next]);
				
				add(c.needs, components[next].needs, components[next].needs[c.idx]);
			}
		}
        
        needs = components[n].needs;
        for (int idx: defaultComponents) {
        	sb.append(idx + " " + needs[idx] + "\n");
		}
        
        System.out.println(sb);
	}
	
	static void add(int[] src, int[] dest, int mul) {
		for (int idx: defaultComponents) {
			dest[idx] += src[idx] * mul;
		}
	}
	
	static class Component {
		int idx, indegree;
		int[] needs;
		public Component(int idx, int n) {
			this.idx = idx;
			this.needs = new int[n];
		}
	}
}

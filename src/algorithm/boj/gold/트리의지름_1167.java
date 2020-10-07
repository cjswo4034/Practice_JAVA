package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름_1167 {
	static int dist, index;
	static List<Node>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		
		for (int i = 0, tmp; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tmp = Integer.parseInt(st.nextToken());
			
			list[tmp] = new ArrayList<>();
			
			while (true) {
				int idx = Integer.parseInt(st.nextToken());
				if (idx == -1) break;
				int weight = Integer.parseInt(st.nextToken());
				
				list[tmp].add(new Node(idx, weight));
			}
		}
		
		// 백준 팁
		// 1) 하나의 터미널 노드에서 가장 거리가 먼 노드 구하기
		// 2) 1)에서 구한 노드에서 가장 거리가 먼 노드까지의 길이

		visited = new boolean[n + 1];
		solution(1, 0);
		
		Arrays.fill(visited, false);
		solution(index, 0);
		
		System.out.println(dist);
	}
	
	// node 로부터 가장 거리가 먼 노드의 idx를 구한다.
	static void solution(int root, int weight) {
		if (visited[root]) return;
		visited[root] = true;
		
		if (dist < weight) {
			dist = weight;
			index = root;
		}
		
		for (Node node : list[root])
			solution(node.idx, weight + node.weight);
	}
	
	static class Node {
		int idx, weight;
		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
	}
}

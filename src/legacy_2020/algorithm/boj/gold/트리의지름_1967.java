package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Gold 4
public class 트리의지름_1967 {
	static int result;
	static List<Node>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) list[i] = new ArrayList<>();
		
		list[0].add(new Node(1, 0));
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, w));
		}
		
		solution(0);
		System.out.println(result);
	}

	// 이진트리 X, 두 개 이상일 수 있음
	static int solution(int root) {
		int b1 = 0, b2 = 0, tmp;
		for(Node node: list[root]) {
			tmp = solution(node.child) + node.weight;
			if (tmp > b1) { b2 = b1; b1 = tmp; }
			else if (tmp > b2) b2 = tmp;
		}
		result = Math.max(result, b1 + b2);
		return b1;
	}
	
	static class Node {
		int child, weight;
		public Node(int child, int weight) {
			this.child = child;
			this.weight = weight;
		}
	}
}

package algorithm.prev.boj.DFS;

import java.util.LinkedList;

public class _1260 {
	static LinkedList<Integer> list[];
	static boolean check[];
	
	static void dfs(int node) {
		check[node] = true;
		for(int i = 0 ; i < list[node].size() ; i++) {
			int next = list[node].get(i);
			if(!check[next]) {
				dfs(node);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
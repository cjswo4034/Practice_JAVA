package algorithm.prev.boj.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class _1260 {
	static LinkedList<Integer> list[];
	static boolean check[];
	static Queue<Integer> q;
	
	static void bfs(int start) {
		q.add(start);
		check[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int i = 0 ; i < list[current].size(); i++) {
				int next = list[current].get(i);
				if(!check[next]) {
					q.add(next);
					check[next] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

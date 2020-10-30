package algorithm.prev.toss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class test {
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<List<Integer>> arr = new ArrayList<>();
		List<Integer> line = new ArrayList<>();
		String input = br.readLine();
		
		for (int i = 0; i < input.length(); i++) {
			char tmp = input.charAt(i);
			if (tmp == ';') {
				arr.add(line);
				line = new ArrayList<>();
			} else if (tmp != ' ') {
				line.add(tmp - '0');
			}
		}
		arr.add(line);

		int answer = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(0).size(); j++) {
				if (arr.get(i).get(j) == 0) {
					answer += nearbyTable(arr, i, j);
				}
			}
		}
		System.out.println(answer);
		

		answer = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(0).size(); j++) {
				if (arr.get(i).get(j) == 1) {
					answer = Math.max(answer, bfs(arr, i, j));
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static int bfs(List<List<Integer>> arr, int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r, c));
		
		boolean[][] visited = new boolean[arr.size()][arr.get(0).size()];
		visited[r][c] = true;
		
		int ret = 0;
		while(!q.isEmpty()) {
			Pair curr = q.poll();
			System.out.println(curr.row + " : " + curr.col);
			for (int i = 0; i < 4; i++) {
				int nr = curr.row + dir[i][0];
				int nc = curr.col + dir[i][1];
				if (nr >= arr.size() || nc >= arr.size() || nr < 0 || nc < 0) continue;
				if (arr.get(nr).get(nc) == 1) ret++;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.row + dir[i][0];
				int nc = curr.col + dir[i][1];
				
				if (nr >= arr.size() || nc >= arr.size() || nr < 0 || nc < 0) continue;
				
				if (visited[nr][nc]) continue;
				visited[nr][nc] = true;
				
				if (arr.get(nr).get(nc) == 1) {
					q.add(new Pair(nr, nc));
				}
			}
		}
		
		return ret;
	}
	
	static class Pair {
		int row, col;
		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int nearbyTable(List<List<Integer>> arr, int row, int col) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int nr = row + dir[i][0];
			int nc = col + dir[i][1];
			if (nr >= arr.size() || nc >= arr.size() || nr < 0 || nc < 0) continue;
			if (arr.get(nr).get(nc) == 1) ret++;
		}
		return ret;
	}
}

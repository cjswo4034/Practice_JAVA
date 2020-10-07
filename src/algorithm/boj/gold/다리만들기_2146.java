package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 다리만들기_2146 {
	static int n, aria, result;
	static int[][] arr;
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited;
	
	static List<List<Aria>> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;	
        
        n = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        
        arr = new int[n][n];
        visited = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || arr[i][j] == 0) continue;
				list.add(new ArrayList<>());
				divideAria(i, j);
				aria++;
			}
		}
        
        // 2. 지역의 외곽에서 다른 지역을 만날 때까지의 최소 거리를 구한다.
        for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (i == j) continue;
				
				for (Aria from : list.get(i)) {
					for (Aria to : list.get(j)) {
						result = Math.min(result, calculate(from, to));
					}
				}
			}
		}
        
        System.out.println(result);
	}
	
	static int calculate(Aria from, Aria to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y) - 1; 
	}

    // 1. 지역을 구분하고 그 지역의 외곽을 저장한다.
	static void divideAria(int x, int y) {
		if (arr[x][y] == 0) return;
		if (visited[x][y]) return;
		visited[x][y] = true;
		
		arr[x][y] = aria + 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if (arr[nx][ny] == 0) {
				Aria tmp = new Aria(x, y);
				if (!list.get(aria).contains(tmp))
					list.get(aria).add(tmp);
			}
			
			divideAria(nx, ny);
		}
	}
	
	static class Aria {
		int x, y;

		public Aria(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Aria tmp = (Aria)obj;
			return this.x == tmp.x && this.y == tmp.y;
		}
	}
}

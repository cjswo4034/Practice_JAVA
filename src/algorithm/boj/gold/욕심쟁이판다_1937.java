package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다_1937 {
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] mem = new int[n][n];
        
        for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				mem[i][j] = -1;
			}
		}
        
        int res = 0;
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res = Math.max(res, dfs(arr, mem, i, j));
			}
		}
        
        System.out.println(res);
	}
	
	static int dfs(int[][] arr, int[][] mem, int x, int y) {
		if (mem[x][y] != -1) return mem[x][y];
		
		mem[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr.length) continue;
			if (arr[x][y] >= arr[nx][ny]) continue;
			
			mem[x][y] = Math.max(mem[x][y], dfs(arr, mem, nx, ny) + 1);
		}
		
		return mem[x][y];
	}
}

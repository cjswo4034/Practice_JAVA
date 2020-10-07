package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로_14890 {
	static int n, l;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map1[i][j] = Integer.parseInt(st.nextToken());
				map2[j][i] = map1[i][j];
			}
		}
        
        System.out.println(solve(map1) + solve(map2));
	}
	
	static int solve(int[][] map) {
		int res = 0;
		
		for (int row = 0; row < n; row++) {
			boolean flag = true;
			boolean[] visited = new boolean[n];
			
			for (int col = 0; col < n; col++) {
				// 1 차이
				if (col > 0 && Math.abs(map[row][col - 1] - map[row][col]) > 1) {
					flag = false;
					break;
				}
				
				// 내리막 확인
				if (col + 1 < n && map[row][col] - 1 == map[row][col + 1]) {
					if (check(visited, map[row], col + 1, col + l)) flag = true;
					else {
						flag = false;
						break;
					}
				}
				
				// 오르막 확인
				if (col - 1 >= 0 && map[row][col] - 1 == map[row][col - 1]) {
					if (check(visited, map[row], col - l, col - 1)) flag = true;
					else {
						flag = false;
						break;
					}
				}
			}
			
			if (flag) res++;
		}
		return res;
	}
	
	static boolean check(boolean[] visited, int[] row, int from, int to) {
		if (from < 0 || to >= n) return false;
		
		int num = row[from];
		for (int i = from; i <= to; i++) {
			if (num != row[i]) return false;
			if (visited[i]) return false;
		}
		
		Arrays.fill(visited, from, to + 1, true);
		
		return true;
	}
}

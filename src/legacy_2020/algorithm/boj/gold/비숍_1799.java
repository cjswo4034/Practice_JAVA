package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비숍_1799 {
	static int n, result1, result2;
	static int[][] map;
	static boolean odd;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

        backtracking(0, 0, 1, false);
        odd = true;
        backtracking(0, 0, 0, true);
        System.out.println(result1 + result2);
	}
	
	static void backtracking(int cnt, int row, int col, boolean first) {
		if (row == n) {
			if (odd) result1 = Math.max(result1, cnt);
			else result2 = Math.max(result2, cnt);
			return;
		}
		
		if (col >= n) {
			backtracking(cnt, row + 1, first ? 1 : 0, !first);
			return;
		}
		
		if (map[row][col] == 1 && check(row, col)) {
			map[row][col] = 2;
			
			backtracking(cnt + 1, row, col + 2, first);

			map[row][col] = 1;
		}
		
		backtracking(cnt, row, col + 2, first);
	}
	
	static boolean check(int row, int col) {
		int nr = row - 1, nc1 = col + 1, nc2 = col - 1;
		while (nr >= 0) {
			if (nc1 < n && map[nr][nc1] == 2) return false;
			if (nc2 >= 0 && map[nr][nc2] == 2) return false;
			nr--; nc1++; nc2--;
		}
		return true;
	}
}

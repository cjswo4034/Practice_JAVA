package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기2_17136 {
	static int result = 987654321;
	static int[] colors = {0, 5, 5, 5, 5, 5};
	static int[][] arr = new int[10][10];
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(result == 987654321 ? -1 : result);
	}
	
	// 행, 열, 남은 1의 개수, 사용한 색종이 수
	private static void dfs(int r, int c, int color) {
		if (color >= result) return;
		
		if (c == 10) {
			dfs(r + 1, 0, color);
			return;
		}
		
		if (r == 10) {
			result = color;
			return;
		}
		
		if (arr[r][c] == 0) {
			dfs(r, c + 1, color);
			return;
		}

		// 5 ~ 1까지 탐색
		for (int i = 5; i > 0; i--) {
			// colors[i] 사용할 수 있는지 확인 (개수 및 유효범위)
			if (colors[i] == 0 || !fillable(r, c, i)) continue;
			
			// 영역에 방문 표시 && 색종이 개수 감소
			fill(r, c, i, 0);
			colors[i]--;
			
			dfs(r, c + i, color + 1);
			
			// 영역에 방문 표시 제거 && 색종이 개수 증가
			fill(r, c, i, 1);
			colors[i]++;
		}
	}
	
	private static boolean fillable(int r, int c, int size) {
		if (r + size > 10 || c + size > 10) return false;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[r + i][c + j] == 0) return false;
			}
		}
		
		return true;
	}
	
	private static void fill(int r, int c, int color, int value) {
		for (int i = 0; i < color; i++) {
			for (int j = 0; j < color; j++) {
				arr[r + i][c + j] = value;
			}
		}
	}
}

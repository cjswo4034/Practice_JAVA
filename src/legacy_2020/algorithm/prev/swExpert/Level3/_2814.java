package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2814 {
	static int n, m, result;
	static int[][] map, dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[] check2;
	static boolean[][] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n + 1][n + 1];
			check2 = new boolean[n + 1];
			check = new boolean[n + 1][n + 1];
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
				map[y][x] = 1;
			}
			
			result = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if(!check[i][j] && map[i][j] == 1 && !check2[i] && !check2[j]) {
						check[i][j] = check[j][i] = true;
						check2[i] = check2[j] = true;
						dfs(j, 1);
						check[i][j] = check[j][i] = false;
						check2[i] = check2[j] = false;
					}
				}
			}
			
			sb.append(result + 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int y, int depth) {
		if(depth > result) {
			result = depth;
		}
		
		for (int i = 1 ; i <= n ; i++) {
			if(map[y][i] == 1 && !check[y][i] && !check2[i]) {
				check[y][i] = check[i][y] = true;
				check2[i] = true;
				dfs(i, depth + 1);
				check[y][i] = check[i][y] = false;
				check2[i] = false;
			}
		}
	}
	
	static class Pair{
		int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

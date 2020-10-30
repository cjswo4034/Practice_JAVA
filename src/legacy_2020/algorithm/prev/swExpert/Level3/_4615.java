package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4615 {
	static int n, m, cnt1, cnt2;
	static int[][] map;
	static int[][] dir = {{0, 1}, {0, -1}, {1,0}, {-1,0},
							{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
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
			cnt1 = 0;
			cnt2 = 0;
			
			map = new int[n][n];			
			map[n / 2][n / 2] = 2;
			map[n / 2][n / 2 - 1] = 1;
			map[n / 2 - 1][n / 2] = 1;
			map[n / 2 - 1][n / 2 - 1] = 2;
			
			int x, y, turn;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				turn = Integer.parseInt(st.nextToken());
				
				func(x - 1, y - 1, turn);
			}
			result();
			sb.append(cnt1 + " " + cnt2);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void result() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j] == 1) {
					cnt1++;
					continue;
				}
				if(map[i][j] == 2) {
					cnt2++;
				}
			}
		}
	}
	
	static void func(int x1, int y1, int turn) {
		map[x1][y1] = turn;
		for (int i = 0; i < dir.length; i++) {
			int nx = x1 + dir[i][0];
			int ny = y1 + dir[i][1];
			
			while(nx >= 0 && ny >= 0 && nx < map.length && ny < map.length) {
				if(map[nx][ny] == turn) {
					int tmpX = x1 + dir[i][0];
					int tmpY = y1 + dir[i][1];
					while(map[tmpX][tmpY] != turn) {
						map[tmpX][tmpY] = turn;
						tmpX += dir[i][0];
						tmpY += dir[i][1];
					}
					break;
				}
				if(map[nx][ny] == 0) {
					break;
				}
				nx = nx + dir[i][0];
				ny = ny + dir[i][1];
			}
		}
	}
}

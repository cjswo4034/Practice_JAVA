package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.StringTokenizer;


public class 혁진이의_프로그램_검증 {
	private static boolean result;
	private static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		char[][] map;
		boolean[][][][] check;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			boolean flag = false; result = false;
			
			map = new char[h][w];
			check = new boolean[h][w][4][16];
			
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (char ch : map[i]) if (ch == '@') flag = true;
			}
			
			if (flag) dfs(0, 0, 1, 0, map, check);
			
			sb.append(result ? "YES" : "NO").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static void dfs(int x, int y, int direction, int mem, char[][] map, boolean[][][][] check) {
		if (map.length <= x || map[0].length <= y) return;
		if (check[x][y][direction][mem]) return;
		else check[x][y][direction][mem] = true;
		if (map[x][y] == '@') {
			result = true;
			return;
		}
		
		int nx, ny;
		boolean qm = false;
		switch (map[x][y]) {
		case '<': direction = 0; break;
		case '>': direction = 1; break;
		case '^': direction = 2; break;
		case 'v': direction = 3; break;
		case '_': direction = mem != 0 ? 0 : 1; break;
		case '|': direction = mem != 0 ? 2 : 3; break;
		case '?': qm = true; break;
		case '.': break;
		case '+': mem = (mem + 1) % 16; break;
		case '-': mem = mem <= 0 ? 15 : mem - 1; break;
		default: mem = map[x][y] - '0'; break;
		}
		
		for (int i = direction; i < 4 + direction; i++) {
			nx = (x + dir[i % 4][0]) % map.length;
			ny = (y + dir[i % 4][1]) % map[0].length;
			if (nx < 0) nx = map.length - 1;
			if (ny < 0) ny = map[0].length - 1;

			dfs(nx, ny, i % 4, mem, map, check);
			if (!qm) break;
		}
	}
}

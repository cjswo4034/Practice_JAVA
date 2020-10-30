package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class _1216_x {
	static int result;
	static char[][] map1 = new char[101][101];
	static char[][] map2 = new char[101][101];
	static boolean[][] check = new boolean[101][101];;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				map1[i] = br.readLine().toCharArray();
			}
			trans();
			result = 0;
			for (int i = 0; i < 100; i++) {
				Arrays.fill(check, false);
				check[0][99] = true;
				solve(map1, i, 0, 99);
				Arrays.fill(check, false);
				check[0][99] = true;
				solve(map2, i, 0, 99);
			}
			
			bw.write("#" + t + " " + (result + 1) + "\n");
			bw.flush();
		}
	}
	
	static void solve(char[][] map, int x, int l, int r) {
		if(l > r || result >= r - l) {
			return;
		}
		
		if(check(map, x, l, r)) {
			result = Math.max(result, r - l);
			return;
		}
		
		if(!check[l + 1][r]) {
			check[l + 1][r] = true;
			solve(map, x, l + 1, r);
		}
		if(!check[l][r - 1]) {
			check[l][r - 1] = true;
			solve(map, x, l, r - 1);
		}
	}
	
	static boolean check(char[][] map, int x, int l, int r) {
		boolean check = true;
		while(l <= r) {
			if(map[x][l++] != map[x][r--]) {
				check = false;
				break;
			}
		}
		
		return check;
	}
	
	static void trans() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j <= i; j++) {
				map2[i][j] = map1[j][i];
				map2[j][i] = map1[i][j];
			}
		}
	}
}

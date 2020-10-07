package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _1215 {
	static int cnt, result;
	static char[][] map = new char[8][8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			cnt = Integer.parseInt(br.readLine());
			
			for(int i = 0 ; i < 8 ; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			result = 0;
			solve();
			
			bw.write("#" + t + " " + result + "\n");
			bw.flush();
		}
	}
	
	static void solve() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(i + cnt <= 8) {
					String tmp = toString(i, j, 0);
					if(check(tmp)) {
						result++;
					}
				}
				if(j + cnt <= 8) {
					String tmp = toString(i, j, 1);
					if(check(tmp)) {
						result++;
					}
				}
			}	
		}
	}
	
	static boolean check(String str) {
		String l = str.substring(0, str.length() / 2);
		StringBuilder r;
		if(str.length() % 2 == 0) {
			r = new StringBuilder(str.substring(str.length() / 2, str.length()));
		} else {
			r = new StringBuilder(str.substring(str.length() / 2 + 1, str.length()));
		}
		r.reverse();
		
		return l.equals(r.toString());
	}
	
	static String toString(int idxX, int idxY, int mode) {
		StringBuilder sb = new StringBuilder();
		if(mode == 1) { // 행
			for(int i = idxY ; i < idxY + cnt ; i++) {
				sb.append(map[idxX][i]);
			}
		} else {
			for(int i = idxX ; i < idxX + cnt ; i++) {
				sb.append(map[i][idxY]);
			}
		}
		
		return sb.toString();
	}
}

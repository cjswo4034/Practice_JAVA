package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4579_x {
	static char[] chs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			chs = br.readLine().toCharArray();

			int l = 0, r = chs.length - 1;

			boolean check = true;
			while (l < r) {
				if(chs[l] == '*' || chs[r] == '*') {
					if (chs[l] == '*') {
						r = find(l, r);
						l++;
					} else{
						l = find(r, l);
						r--;
					}
					continue;
				}
				if (chs[l] != chs[r]) {
					check = false;
					break;
				}
				l++;
				r--;
			}

			if (check) {
				sb.append("Exist");
			} else {
				sb.append("Not Exist");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int find(int star, int idx) {
		char ch = chs[idx];
		if(star < idx)
			while(star <= idx) {
				idx--;
				if(chs[idx] != ch) {
					return idx;
				}
			}
		else
			while(star >= idx) {
				idx++;
				if(chs[idx] != ch) {
					return idx;
				}
			}
		
		return idx;
	}
}

package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _5293_x {
	static int cnt, total;
	static int[] count = new int[5];
	
	static String result;
	static String[] str = { "00", "01", "10", "11" };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cnt = total = 0;
			
			for (int i = 1; i <= 4; i++) {
				count[i] = Integer.parseInt(st.nextToken());
				total += count[i];
			}
			cnt = total;
			
			if(total == 1) {
				for (int i = 1; i <= 4; i++) {
					if(count[i] > 0) {
						bw.write("#" + t + " " + str[i - 1] + "\n");
						break;
					}
				}
				continue;
			}
			
			result = "";
			for (int i = 0; i < 4; i++) {
				if(count[i + 1] > 0)
					func(i + 1, str[i].substring(0, 1));
			}
			
			if(result.equals("")) {
				bw.write("#" + t + " " + "impossible" + "\n");
			} else {
				bw.write("#" + t + " " + result + "\n");	
			}
		}
		bw.flush();
	}
	
	static void dfs(int idx, String sb) {
		if(Math.abs(count[2] - count[3]) > 1) {
			return;
		}
		if(sb.length() > total + 1) {
			return;
		}
		if(cnt == 0) {
			if(sb.length() == total + 1) {
				result = sb;
				return;
			} else {
				return;
			}
		}
		
		if(idx == 1 || idx == 3) {
			if(count[1] < 1 && count[2] < 1) {
				return;
			}
			if(count[1] > 0) {
				func(1, sb);
			}
			if(count[2] > 0) {
				func(2, sb);
			}
		} else if(idx == 2 || idx == 4) {
			if(count[3] < 1 && count[4] < 1) {
				return;
			}
			if(count[3] > 0) {
				func(3, sb);
			}
			if(count[4] > 0) {
				func(4, sb);
			}
		}
	}
	
	static void func(int idx, String sb) {
		count[idx]--;
		cnt--;
		dfs(idx, sb + str[idx - 1].substring(1, 2));
		cnt++;
		count[idx]++;
	}
}

package algorithm.prev.swExpert.Level4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Binary_Indexed_Tree {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int[] arr;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			arr = initArray(n);
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0, start = arr.length / 2; i < n; i++) {
				addEl(arr, start + i, Integer.parseInt(st.nextToken()));
			}
			
			int cmd, a, b;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				cmd = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if (cmd == 1) addEl(arr, arr.length / 2 + a - 1, b);
				else sb.append(calculate(arr,
										 arr.length / 2 + a - 1,
										 arr.length / 2 + b - 1,
										 1))
						.append(" ");
			}
			
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	private static int calculate(int[] arr, int l, int r, int pos) {
		int dL = pos, dR = pos;
		while (dR <= arr.length) {
			dL = (dL << 1);
			dR = (dR << 1) + 1;
		}
		
		dL >>= 1;
		dR >>= 1;
		
		int ans = 0;
		
		if (dL > r || dR < l) ans += 0;
		else if (dL >= l && dR <= r) ans += arr[pos];
		else {
			ans += calculate(arr, l, r, pos * 2) +
					calculate(arr, l, r, pos * 2 + 1);
		}
		
		return ans;
	}
	
	private static void addEl(int[] arr, int pos, int num) {
		while (0 < pos) {
			arr[pos] += num;
			pos >>= 1;
		}
	}
	
	private static int[] initArray(int n) {
		int len = 1;
		while (n > (len <<= 1));
		
		return new int[(len << 1) + 1];
	}
}
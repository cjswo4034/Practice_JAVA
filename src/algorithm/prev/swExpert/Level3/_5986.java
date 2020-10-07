package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5986 {
	static boolean [] check = new boolean[1000 + 1];
	static int [] array;
	static int n, total, result;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = 1000;
		init();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			result = 0;
			
			for(int i = 0 ; i < total ; i++) {
				if(array[i] > n) break;
				for (int j = i; j < total; j++) {
					if(array[j] > n) break;
					for (int j2 = j; j2 < total; j2++) {
						if(array[j2] > n) break;
						if(array[i] + array[j] + array[j2] == n) {
							result++;
						}
					}
				}
			}
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int depth, int tmp, int sum) {
		if(n == sum && depth == 2) {
			result++;
			return;
		}
		
		if(n < sum || depth >= 3) {
			return;
		}
		
		for(int i = tmp ; i < total ; i++) {
			if(array[i] < n) {
				dfs(depth + 1, i, sum + array[i]);
			} else {
				return;
			}
		}
	}
	
	static void init() {
		for (int i = 2; i <= n; i++) {
			if(!check[i]) {
				for (int j = i + i; j <= n; j += i) {
					check[j] = true;
				}
				total++;
			}
		}
		
		array = new int[total];
		int idx = 0;
		for (int i = 2; i <= n; i++) {
			if (!check[i]) {
				array[idx++] = i;
			}
		}
	}
}

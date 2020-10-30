package algorithm.prev.swExpert.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _3307 {
	static int [] array;
	static int [] dp;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			
			int n = Integer.parseInt(br.readLine());
			array = new int[n];
			dp = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			lis();
			
			sb.append(size + 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void lis() {
		size = 0;
		dp[0] = array[0];
		for(int i = 1; i < array.length ; i++) {
			if(array[i] > dp[size]) {
				size++;
				dp[size] = array[i];
				continue;
			}
			int idx = lowerBound(i);
			dp[idx] = array[i];
		}
	}
		
	static int lowerBound(int idx) {
		int num = array[idx];
		int l = 0, r = size;
		int m;
		
		while(l < r) {
			m = (l + r) / 2;
			if(dp[m] < num) l = m + 1;
			else r = m;
		}
		
		return l;
	}
	
	static int test(int idx) {
		int num = array[idx];
		int result = 0;
		for (int i = 0; i < size + 1; i++) {
			if(dp[i] > num) {
				result = i;
				break;
			}
		}
		return result;
	}
}

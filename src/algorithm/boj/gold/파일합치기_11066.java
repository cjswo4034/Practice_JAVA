package algorithm.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파일합치기_11066 {
	static int[] a, b;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0) {
        	int k = Integer.parseInt(br.readLine());
        	StringTokenizer st = new StringTokenizer(br.readLine());
            
        	a = new int[k + 1];
        	b = new int[k + 1];
        	dp = new int[k + 1][k + 1];
        	for (int i = 1; i <= k; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = b[i - 1] + a[i];
			}
        	
        	for (int size = 1; size < k; size++) {
				for (int pos = 1; pos + size <= k; pos++) {
					int x = pos + size;
					dp[pos][x] = Integer.MAX_VALUE;
					for (int divider = pos; divider < x; divider++) {
						dp[pos][x] = Math.min(dp[pos][x], dp[pos][divider] + dp[divider + 1][x] + b[x] - b[pos - 1]);
					}
				}
			}
        	
        	System.out.println("1: " + dp[1][k]);
        	
        	for (int i = 0; i <= k; i++) {
				Arrays.fill(dp[i], 987654321);
			}
        	
        	System.out.println("2: " + dpf(1, k));
        }
	}
	
	// 더빠름
	static int dpf(int tx, int ty) {
		if (dp[tx][ty] != 987654321) return dp[tx][ty];
		if (tx == ty) return dp[tx][ty] = 0;
		if (tx + 1 == ty) return dp[tx][ty] = a[tx] + a[ty];
		
		for (int divider = tx; divider < ty; divider++) {
			int left = dpf(tx, divider);
			int right = dpf(divider + 1, ty);
			dp[tx][ty] = Math.min(dp[tx][ty], left + right);
		}
		
		return dp[tx][ty] += b[ty] - b[tx - 1];
	}
}

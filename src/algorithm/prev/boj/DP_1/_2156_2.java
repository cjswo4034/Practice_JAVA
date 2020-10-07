package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2156_2 {
	static int A[] = new int[10001];
	static int DP[][] = new int[10001][3];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		DP[1][1] = DP[1][2] = A[1];
		
		for(int i = 2 ; i <= n ; i++) {
			DP[i][0] = Math.max(DP[i - 1][0], Math.max(DP[i - 1][1], DP[i - 1][2]));
			DP[i][1] = DP[i - 1][0] + A[i];
			DP[i][2] = DP[i - 1][1] + A[i];
		}		
		
		System.out.println(Math.max(DP[n][0], Math.max(DP[n][1], DP[n][2])));
	}
}

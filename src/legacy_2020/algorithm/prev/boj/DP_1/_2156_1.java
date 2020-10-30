package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2156_1 {
	static int A[] = new int[10001];
	static int DP[] = new int[10001];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		DP[1] = A[1];
		DP[2] = A[1] + A[2];
		
		for(int i = 3 ; i <= n ; i++) {
			DP[i] = DP[i - 1];
			if(DP[i] < DP[i - 2] + A[i])
				DP[i] = DP[i - 2] + A[i];
			if(DP[i] < DP[i - 3] + A[i] + A[i - 1])
				DP[i] = DP[i - 3] + A[i] + A[i - 1];
		}
		
		System.out.println(DP[n]);
	}
}

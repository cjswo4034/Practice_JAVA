package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1912 {
	static int A[] = new int[301];
	static int DP[] = new int[301];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= n ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		DP[1] = A[1];
		DP[2] = A[1] + A[2];
		for(int i = 3; i <= n ; i++) {
			DP[i] = Math.max(DP[i - 2], DP[i - 3] + A[i - 1]) + A[i];
		}
		System.out.println(DP[n]);
	}

}
package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2193 {
	static int DP[][] = new int[91][2];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		DP[1][1] = 1;
		for(int i = 2 ; i <= n ; i++) {
			DP[i][0] = DP[i - 1][0] + DP[i - 1][1];
			DP[i][1] = DP[i - 1][0];
		}
		
		System.out.println(DP[n][0] + DP[n][1]);
	}

}

package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11057 {
	static int DP[][] = new int[1001][10];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i <= 9 ; i++) {
			DP[1][i] = 1;
		}
		
		for(int i = 2 ; i <= n ; i++) {
			for (int j = 0; j < 10; j++) {
				for(int k = 0 ; k <= j ; k++) {
					DP[i][j] = (DP[i][j] + DP[i - 1][k]) % 10007;
				}
			}
		}
		
		long result = 0;
		
		for(int i = 0 ; i <= 9 ; i++) {
			result += DP[n][i];
		}
		
		System.out.println(result % 10007);
	}

}

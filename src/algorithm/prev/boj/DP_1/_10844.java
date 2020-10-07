package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10844 {
	static int DP[][] = new int[101][10];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i <= 9 ; i++) {
			DP[1][i] = 1;
		}
		
		for(int i = 2 ; i <= n ; i++) {
			for (int j = 0; j < 10; j++) {
				if(j == 0) DP[i][j] = DP[i - 1][j + 1];
				else if(j == 9) DP[i][j] = DP[i - 1][j - 1];
				else DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j + 1]) % 1000000000;
			}
		}
		
		long result = 0;
		
		for(int i = 0 ; i <= 9 ; i++) {
			result += DP[n][i];
		}
		
		System.out.println(result % 1000000000);
	}

}

package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9465 {
	static int DP[][] = new int[100001][2];
	static int A[][] = new int[100001][2];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < 2 ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j <= n ; j++) {
					A[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			DP[1][0] = A[1][0];
			DP[1][1] = A[1][1];
			
			for(int i = 2 ; i <= n ; i++) {
				DP[i][0] = Math.max(DP[i - 1][1], DP[i - 2][1]) + A[i][0];
				DP[i][1] = Math.max(DP[i - 1][0], DP[i - 2][0]) + A[i][1];
			}
			
			System.out.println(Math.max(DP[n][0],DP[n][1]));
		}
	}

}

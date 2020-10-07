package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11054 {
	static int A[] = new int[1001];
	static int DP[][] = new int[1001][2];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n ; i++) {
			DP[i][0] = 1;
			DP[i][1] = 1;
			for(int j = 1; j < i ; j++) {
				if(A[i] < A[j]) {
					DP[i][1] = Math.max(DP[i][1], Math.max(DP[j][0], DP[j][1]) + 1); 
				}
				if(A[i] > A[j]) {
					DP[i][0] = Math.max(DP[i][0], DP[j][0] + 1);
				}
			}
			ans = Math.max(ans,  Math.max(DP[i][0], DP[i][1]));
		}
		System.out.println(ans);
	}

}
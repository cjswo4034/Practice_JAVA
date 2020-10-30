package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11055 {
	static int A[] = new int[1001];
	static int DP[] = new int[1001];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 1;
		
		for(int i = 1; i <= n ; i++) {
			DP[i] = A[i];
			for(int j = 1; j < i ; j++) {
				if(A[i] > A[j]) {
					DP[i] = Math.max(DP[i], DP[j] + A[i]);
				}
			}
			max = Math.max(max, DP[i]);
		}
		System.out.println(max);
	}

}

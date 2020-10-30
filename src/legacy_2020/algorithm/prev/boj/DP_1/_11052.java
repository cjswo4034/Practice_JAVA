package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11052 {
	static int A[] = new int[10001];
	static int DP[] = new int[10001];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n ; i++) {
			DP[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 2 ; i <= n ; i++) {
			for(int j = 1 ; j < i ; j++) {
				DP[i] = Math.max(DP[i - j] + DP[j], DP[i]);
			}
		}
		
		System.out.println(DP[n]);
	}

}

package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2133 {
	static int DP[] = new int[100001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		DP[1] = 1;
		for(int i = 1 ; i <= n ; i++) {
			DP[i] = i;
			for(int j = 1 ; j * j <= i ; j++) {
				DP[i] = Math.min(DP[i], DP[i - (j * j)] + 1);
			}
		}
		System.out.println(DP[n]);
	}

}

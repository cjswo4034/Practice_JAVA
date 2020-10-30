package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1463 {
	static int DP[] = new int[1000001];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		DP[1] = 0;
		for (int i = 2; i <= n; i++) {
			DP[i] = DP[i - 1] + 1;
			if(i % 3 == 0)
				DP[i] = Math.min(DP[i], DP[i / 3] + 1);
			if(i % 2 == 0)
				DP[i] = Math.min(DP[i], DP[i / 2] + 1);
		}
		System.out.println(DP[n]);
	}

}

package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9095 {
	static int DP[] = new int[11];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			DP[0] = DP[1] = 1;
			DP[2] = DP[1] + DP[0];
			for (int i = 3; i <= n; i++) {
				DP[i] = DP[i - 1] + DP[i - 2] + DP[i - 3];
			}
			System.out.println(DP[n]);
		}
	}

}

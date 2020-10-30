package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11727 {
	static int DP[] = new int[1001];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		DP[0] = DP[1] = 1;
		for (int i = 2; i <= n; i++) {
			DP[i] = (DP[i - 1] + 2 * DP[i - 2]) % 10007;
		}
		System.out.println(DP[n]);
	}

}

package algorithm.prev.boj.DP_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2011 {
	static long DP[] = new long[5001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int n = str.length();
		
		DP[0] = 1;
		
		/*for(int i = 1; i <= n ; i++) {
			int x = str.charAt(i - 1) - '0';
			
			if(x >= 1 && x <= 9)
				DP[i] = DP[i - 1];
			
			if(i == 1) continue;
			if(str.charAt(i - 2) == '0') continue;
			
			int tmp = Integer.parseInt(str.substring(i - 2, i));
			if(tmp >= 10 && tmp <= 26) {
				DP[i] = (DP[i] + DP[i - 2]) % 1000000; 
			}
		}
		
		System.out.println("\n" + DP[n]);*/
		
		DP[1] = str.charAt(0) - '0' != 0 ? 1 : 0;
		for(int i = 2; i <= n ; i++) {
			int tmp = Integer.parseInt(str.substring(i - 2, i));
			if(tmp % 10 == 0) {
				DP[i] = DP[i - 2];
			} else if(tmp > 10 && tmp <= 26){
				DP[i] = (DP[i - 1] + DP[i - 2]) % 1000000;
			} else {
				DP[i] = DP[i - 1];
			}
		}
		
		System.out.println(DP[n]);
	}

}

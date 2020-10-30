package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1850 {
	static long gcd(long a, long b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		
		String str = br.readLine();
		long a = Long.parseLong(str.split(" ")[0]);
		long b = Long.parseLong(str.split(" ")[1]);
		
		long result = gcd(Math.max(a, b), Math.min(a, b));
		
		while(result-- > 0) {
			ans.append(1);
		}
		System.out.println(ans);
	}
}

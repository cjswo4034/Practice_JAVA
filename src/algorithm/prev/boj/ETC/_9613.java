package algorithm.prev.boj.ETC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9613 {
	static int a[];
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());		
		while(t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());			
			int ans = 0;
			
			a = new int[n];
			for(int i = 0 ; i < n ; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < n - 1 ; i++) {
				for(int j = i + 1 ; j < n ; j++) {
					ans += gcd(a[i], a[j]);
				}
			}
			
			System.out.println(ans);
		}
	}
}
